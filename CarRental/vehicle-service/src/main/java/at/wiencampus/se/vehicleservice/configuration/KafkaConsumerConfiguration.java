package at.wiencampus.se.vehicleservice.configuration;

import at.wiencampus.se.common.dto.*;
import at.wiencampus.se.vehicleservice.model.CustomerRental;
import at.wiencampus.se.vehicleservice.model.Vehicle;
import at.wiencampus.se.vehicleservice.repository.CustomerRentalRepository;
import at.wiencampus.se.vehicleservice.repository.VehicleRepository;
import at.wiencampus.se.vehicleservice.service.CurrencyService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.*;

@Configuration
public class KafkaConsumerConfiguration {

    @Value("${kafka.broker1}")
    private String KAFKA_BROKER;
    @Value("${kafka.vehicle.groupid}")
    private String GROUP_ID;

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private CustomerRentalRepository customerRentalRepository;
    @Autowired
    private CurrencyService currencyService;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return props;
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return props;
    }

    @Bean
    public ConsumerFactory<String, VehicleServiceRequest> requestConsumerFactory() {
        JsonDeserializer<VehicleServiceRequest> requestJsonDeserializer = new JsonDeserializer<>();
        requestJsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), requestJsonDeserializer);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, VehicleServiceRequest>> requestListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, VehicleServiceRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(requestConsumerFactory());
        factory.setReplyTemplate(replyTemplate());
        return factory;
    }

    @Bean
    public KafkaTemplate<String, VehicleServiceReply> replyTemplate() {
        return new KafkaTemplate<>(replyProducerFactory());
    }

    @Bean
    public ProducerFactory<String, VehicleServiceReply> replyProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(), new StringSerializer(), new JsonSerializer<VehicleServiceReply>());
    }

    @KafkaListener(topics = "${kafka.vehicle.topic.book.request}", containerFactory = "requestListenerContainerFactory")
    @SendTo()
    public VehicleServiceReply bookListener(VehicleServiceRequest request) {
        VehicleServiceReply reply = new VehicleServiceReply();
        reply.setName(VehicleServiceName.Book);
        try {
            if(request.getName() == VehicleServiceName.Book) {
                Mapper mapper = new DozerBeanMapper();
                CustomerRentalData newCustomerRental = request.getCustomerRental();
                CustomerRental customerRentalEntity = mapper.map(newCustomerRental, CustomerRental.class);
                customerRentalEntity = customerRentalRepository.save(customerRentalEntity);
                CustomerRentalData savedRental = mapper.map(customerRentalEntity, CustomerRentalData.class);
                reply.setCustomerRental(savedRental);
            } else {
                reply.setException(new Exception("Wrong request type: Request Type is not BOOK!"));
            }
        } catch (Exception e) {
            reply.setException(new Exception("Booking service failed!", e));
        }
        return reply;
    }

    @KafkaListener(topics = "${kafka.vehicle.topic.return.request}", containerFactory = "requestListenerContainerFactory")
    @SendTo()
    public VehicleServiceReply returnListener(VehicleServiceRequest request) {
        VehicleServiceReply reply = new VehicleServiceReply();
        reply.setName(VehicleServiceName.Return);
        try {
            if(request.getName() == VehicleServiceName.Return) {
                Mapper mapper = new DozerBeanMapper();
                Optional<CustomerRental> rental = customerRentalRepository.findById(request.getRentalId());
                if (!rental.isPresent()){
                    reply.setIsReturned(false);
                } else {
                    customerRentalRepository.deleteById(request.getRentalId());
                    reply.setIsReturned(true);
                }
            } else {
                reply.setException(new Exception("Wrong request type: Request Type is not RETURN!"));
            }
        } catch (Exception e) {
            reply.setException(new Exception("Returning service failed!", e));
        }
        return reply;
    }

    @KafkaListener(topics = "${kafka.vehicle.topic.new.request}", containerFactory = "requestListenerContainerFactory")
    @SendTo()
    public VehicleServiceReply newVehicleListener(VehicleServiceRequest request) {
        VehicleServiceReply reply = new VehicleServiceReply();
        reply.setName(VehicleServiceName.NewVehicle);
        try {
            if(request.getName() == VehicleServiceName.NewVehicle) {
                Mapper mapper = new DozerBeanMapper();
                VehicleData newVehicle = request.getVehicle();
                Vehicle vehicleEntity = mapper.map(newVehicle, Vehicle.class);
                vehicleEntity.setVehicleId(null);
                vehicleEntity = vehicleRepository.save(vehicleEntity);
                VehicleData savedVehicle = mapper.map(vehicleEntity, VehicleData.class);
                reply.setVehicle(savedVehicle);
            } else {
                reply.setException(new Exception("Wrong request type: Request Type is not NewVehicle!"));
            }
        } catch (Exception e) {
            reply.setException(new Exception("newVehicle service failed!", e));
        }
        return reply;
    }

    @KafkaListener(topics = "${kafka.vehicle.topic.all.request}", containerFactory = "requestListenerContainerFactory")
    @SendTo()
    public VehicleServiceReply getAllVehiclesListener(VehicleServiceRequest request) {
        VehicleServiceReply reply = new VehicleServiceReply();
        reply.setName(VehicleServiceName.AllVehicles);
        try {
            if(request.getName() == VehicleServiceName.AllVehicles) {
                Mapper mapper = new DozerBeanMapper();
                List<Vehicle> vehicles = vehicleRepository.findAll();
                List<VehicleData> vehicleData = new ArrayList<>(vehicles.size());
                for (Vehicle entity : vehicles) {
                    VehicleData dto = mapper.map(entity, VehicleData.class);
                    vehicleData.add(dto);
                }
                reply.setVehicles(vehicleData);
            } else {
                reply.setException(new Exception("Wrong request type: Request Type is not AllVehicles!"));
            }
        } catch (Exception e) {
            reply.setException(new Exception("Get all vehicles service failed!", e));
        }
        return reply;
    }

    @KafkaListener(topics = "${kafka.vehicle.topic.all_with_currency.request}", containerFactory = "requestListenerContainerFactory")
    @SendTo()
    public VehicleServiceReply getAllVehiclesForCurrencyListener(VehicleServiceRequest request) {
        VehicleServiceReply reply = new VehicleServiceReply();
        reply.setName(VehicleServiceName.AllVehiclesForCurrency);
        try {
            if(request.getName() == VehicleServiceName.AllVehiclesForCurrency) {
                Mapper mapper = new DozerBeanMapper();
                List<Vehicle> allVehicles = vehicleRepository.findAll();
                List<VehicleData> vehicleData = new ArrayList<>(allVehicles.size());

                for (Vehicle vehicle : allVehicles) {
                    double convertedPrice = currencyService.getCurrency(vehicle.getCost(), CurrencyEnum.fromValue(request.getCurrency())).getPrice();
                    vehicle.setCost((float) convertedPrice);

                    VehicleData dto = mapper.map(vehicle, VehicleData.class);
                    vehicleData.add(dto);
                }
                reply.setVehicles(vehicleData);
            } else {
                reply.setException(new Exception("Wrong request type: Request Type is not AllVehiclesForCurrency!"));
            }
        } catch (Exception e) {
            reply.setException(new Exception("Get AllVehiclesForCurrency service failed!", e));
        }
        return reply;
    }

    @KafkaListener(topics = "${kafka.vehicle.topic.all_customer_rentals.request}", containerFactory = "requestListenerContainerFactory")
    @SendTo()
    public VehicleServiceReply getAllCustomerRentalsListener(VehicleServiceRequest request) {
        VehicleServiceReply reply = new VehicleServiceReply();
        reply.setName(VehicleServiceName.AllCustomerRentals);
        try {
            if(request.getName() == VehicleServiceName.AllCustomerRentals) {
                Mapper mapper = new DozerBeanMapper();

                //Optional<Customer> customer = customerRepository.findCustomerByCustomerId(request.getCustomerId());
               // List<CustomerRental> customerRentals = customerRentalRepository.getCustomerRentalByCustomer(customer.get());
                List<CustomerRentalData> customerRentalData = new ArrayList<>();

                /*for (CustomerRental rental : customerRentals) {
                    CustomerRentalData dto = mapper.map(rental, CustomerRentalData.class);
                    customerRentalData.add(dto);
                }*/
                reply.setCustomerRentals(customerRentalData);
            } else {
                reply.setException(new Exception("Wrong request type: Request Type is not AllCustomerRentals!"));
            }
        } catch (Exception e) {
            reply.setException(new Exception("Get AllCustomerRentals service failed!", e));
        }
        return reply;
    }

}
