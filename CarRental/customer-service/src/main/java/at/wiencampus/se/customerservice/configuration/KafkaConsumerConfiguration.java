package at.wiencampus.se.customerservice.configuration;

import at.wiencampus.se.common.dto.*;
import at.wiencampus.se.customerservice.model.Customer;
import at.wiencampus.se.customerservice.repository.CustomerRepository;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaConsumerConfiguration {

    @Value("${kafka.broker1}")
    private String KAFKA_BROKER;
    @Value("${kafka.customer.groupid}")
    private String GROUP_ID;

    @Autowired
    private CustomerRepository customerRepository;

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
    public ConsumerFactory<String, CustomerServiceRequest> requestConsumerFactory() {
        JsonDeserializer<CustomerServiceRequest> requestJsonDeserializer = new JsonDeserializer<>();
        requestJsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), requestJsonDeserializer);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, CustomerServiceRequest>> requestListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CustomerServiceRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(requestConsumerFactory());
        factory.setReplyTemplate(replyTemplate());
        return factory;
    }

    @Bean
    public KafkaTemplate<String, CustomerServiceReply> replyTemplate() {
        return new KafkaTemplate<>(replyProducerFactory());
    }

    @Bean
    public ProducerFactory<String, CustomerServiceReply> replyProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(), new StringSerializer(), new JsonSerializer<CustomerServiceReply>());
    }

    @KafkaListener(topics = "${kafka.customer.topic.login.request}", containerFactory = "requestListenerContainerFactory")
    @SendTo()
    public CustomerServiceReply loginListener(CustomerServiceRequest request) {
        CustomerServiceReply reply = new CustomerServiceReply();
        reply.setName(CustomerServiceName.Login);
        try {
            if(request.getName() == CustomerServiceName.Login) {
                Mapper mapper = new DozerBeanMapper();
                LoginData loginData = request.getLoginData();
                Customer customerEntity = customerRepository.findByEmailAndPassword(loginData.getEmail(), loginData.getPassword());
                CustomerData destCustomer = mapper.map(customerEntity, CustomerData.class);
                reply.setCustomerData(destCustomer);
            } else {
                reply.setException(new Exception("Wrong request type: Request Type is not LOGIN!"));
            }
        } catch (Exception e) {
            reply.setException(new Exception("Login service failed!", e));
        }
        return reply;
    }

    @KafkaListener(topics = "${kafka.customer.topic.register.request}", containerFactory = "requestListenerContainerFactory")
    @SendTo()
    public CustomerServiceReply registerListener(CustomerServiceRequest request) {
        CustomerServiceReply reply = new CustomerServiceReply();
        reply.setName(CustomerServiceName.Register);
        try {
            if(request.getName() == CustomerServiceName.Register) {
                Mapper mapper = new DozerBeanMapper();
                CustomerData newCustomer = request.getCustomer();
                Customer customerEntity = mapper.map(newCustomer, Customer.class);
                customerEntity = customerRepository.save(customerEntity);

                CustomerData savedCustomer = mapper.map(customerEntity, CustomerData.class);
                reply.setCustomerData(savedCustomer);
            } else {
                reply.setException(new Exception("Wrong request type: Request Type is not REGISTER!"));
            }
        } catch (Exception e) {
            reply.setException(new Exception("Register service failed!", e));
        }
        return reply;
    }

    @KafkaListener(topics = "${kafka.customer.topic.get_all.request}", containerFactory = "requestListenerContainerFactory")
    @SendTo()
    public CustomerServiceReply getAllListener(CustomerServiceRequest request) {
        CustomerServiceReply reply = new CustomerServiceReply();
        reply.setName(CustomerServiceName.GetAll);
        try {
            if(request.getName() == CustomerServiceName.GetAll) {
                Mapper mapper = new DozerBeanMapper();
                List<Customer> customers = customerRepository.findAll();
                List<CustomerData> customerDataList = new ArrayList<>(customers.size());

                for (Customer entity : customers) {
                    CustomerData dto = mapper.map(entity, CustomerData.class);
                    customerDataList.add(dto);
                }

                reply.setCustomers(customerDataList);
            } else {
                reply.setException(new Exception("Wrong request type: Request Type is not GET_ALL!"));
            }
        } catch (Exception e) {
            reply.setException(new Exception("Get All Customer service failed!", e));
        }
        return reply;
    }
}
