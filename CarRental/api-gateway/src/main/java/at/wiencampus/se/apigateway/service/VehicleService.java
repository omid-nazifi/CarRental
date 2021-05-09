package at.wiencampus.se.apigateway.service;

import at.wiencampus.se.common.dto.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private ReplyingKafkaTemplate<String, VehicleServiceRequest, VehicleServiceReply> requestReplyKafkaTemplate;

    @Value("${kafka.vehicle.topic.book.request}")
    public String REQUEST_TOPIC_BOOK;
    @Value("${kafka.vehicle.topic.book.reply}")
    public String REPLY_TOPIC_BOOK;
    @Value("${kafka.vehicle.topic.return.request}")
    public String REQUEST_TOPIC_RETURN;
    @Value("${kafka.vehicle.topic.return.reply}")
    public String REPLY_TOPIC_RETURN;
    @Value("${kafka.vehicle.topic.all.request}")
    public String REQUEST_TOPIC_ALL_VEHICLE;
    @Value("${kafka.vehicle.topic.all.reply}")
    public String REPLY_TOPIC_ALL_VEHICLE;
    @Value("${kafka.vehicle.topic.all_with_currency.request}")
    public String REQUEST_TOPIC_ALL_VEHICLE_WITH_CURRENCY;
    @Value("${kafka.vehicle.topic.all_with_currency.reply}")
    public String REPLY_TOPIC_ALL_VEHICLE_WITH_CURRENCY;
    @Value("${kafka.vehicle.topic.all_customer_rentals.request}")
    public String REQUEST_TOPIC_ALL_CUSTOMER_RENTALS;
    @Value("${kafka.vehicle.topic.all_customer_rentals.reply}")
    public String REPLY_TOPIC_ALL_CUSTOMER_RENTALS;
    @Value("${kafka.vehicle.topic.new.request}")
    public String REQUEST_TOPIC_NEW_VEHICLE;
    @Value("${kafka.vehicle.topic.new.reply}")
    public String REPLY_TOPIC_NEW_VEHICLE;

    public VehicleData createNew(VehicleData vehicle) {
        VehicleServiceRequest request = new VehicleServiceRequest();
        request.setName(VehicleServiceName.NewVehicle);
        request.setVehicle(vehicle);

        //call MService
        VehicleServiceReply reply = sendAndReceive(request, REQUEST_TOPIC_NEW_VEHICLE, REPLY_TOPIC_NEW_VEHICLE);
        if (reply.getName() == VehicleServiceName.NewVehicle) {
            return reply.getVehicle();
        } else {
            throw new UnsupportedOperationException("Response Type is wrong! (" + reply.getName() + ")");
        }
    }

    public CustomerRentalData createVehicleRental(CustomerRentalData newCustomerRental) {
        VehicleServiceRequest request = new VehicleServiceRequest();
        request.setName(VehicleServiceName.Book);
        request.setCustomerRental(newCustomerRental);

        //call MService
        VehicleServiceReply reply = sendAndReceive(request, REQUEST_TOPIC_BOOK, REPLY_TOPIC_BOOK);
        if (reply.getName() == VehicleServiceName.Book) {
            return reply.getCustomerRental();
        } else {
            throw new UnsupportedOperationException("Response Type is wrong! (" + reply.getName() + ")");
        }
    }

    public boolean returnRentalCar(String rentalId) {
        VehicleServiceRequest request = new VehicleServiceRequest();
        request.setName(VehicleServiceName.Return);
        request.setRentalId(rentalId);

        //call MService
        VehicleServiceReply reply = sendAndReceive(request, REQUEST_TOPIC_RETURN, REPLY_TOPIC_RETURN);
        if (reply.getName() == VehicleServiceName.Return) {
            return reply.getIsReturned();
        } else {
            throw new UnsupportedOperationException("Response Type is wrong! (" + reply.getName() + ")");
        }
    }

    public List<VehicleData> getAllVehicles() {
        VehicleServiceRequest request = new VehicleServiceRequest();
        request.setName(VehicleServiceName.AllVehicles);
        request.setCurrency(null);
        request.setCustomerId(null);
        request.setCustomerRental(null);
        request.setVehicle(null);
        request.setRentalId(null);

        //call MService
        VehicleServiceReply reply = sendAndReceive(request, REQUEST_TOPIC_ALL_VEHICLE, REPLY_TOPIC_ALL_VEHICLE);
        if (reply.getName() == VehicleServiceName.AllVehicles) {
            return reply.getVehicles();
        } else {
            throw new UnsupportedOperationException("Response Type is wrong! (" + reply.getName() + ")");
        }
    }

    public List<CustomerRentalData> getAllCustomerRentalForCustomer(long customerId) {
        VehicleServiceRequest request = new VehicleServiceRequest();
        request.setName(VehicleServiceName.AllCustomerRentals);
        request.setCustomerId(customerId);

        //call MService
        VehicleServiceReply reply = sendAndReceive(request, REQUEST_TOPIC_ALL_CUSTOMER_RENTALS, REPLY_TOPIC_ALL_CUSTOMER_RENTALS);
        if (reply.getName() == VehicleServiceName.AllCustomerRentals) {
            return reply.getCustomerRentals();
        } else {
            throw new UnsupportedOperationException("Response Type is wrong! (" + reply.getName() + ")");
        }
    }

    public List<VehicleData> getAllVehicleForCurrency(String currency) {
        VehicleServiceRequest request = new VehicleServiceRequest();
        request.setName(VehicleServiceName.AllVehiclesForCurrency);
        request.setCurrency(currency);

        //call MService
        VehicleServiceReply reply = sendAndReceive(request, REQUEST_TOPIC_ALL_VEHICLE_WITH_CURRENCY, REPLY_TOPIC_ALL_VEHICLE_WITH_CURRENCY);
        if (reply.getName() == VehicleServiceName.AllVehiclesForCurrency) {
            return reply.getVehicles();
        } else {
            throw new UnsupportedOperationException("Response Type is wrong! (" + reply.getName() + ")");
        }
    }

    private VehicleServiceReply sendAndReceive(VehicleServiceRequest request, String requestTopic, String replyTopic) {
        try {
            ProducerRecord<String, VehicleServiceRequest> record = new ProducerRecord<String, VehicleServiceRequest>(requestTopic, request);
            record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, replyTopic.getBytes()));
            RequestReplyFuture<String, VehicleServiceRequest, VehicleServiceReply> sendAndReceive = requestReplyKafkaTemplate.sendAndReceive(record);

            ConsumerRecord<String, VehicleServiceReply> consumerRecord = sendAndReceive.get();
            VehicleServiceReply reply = consumerRecord.value();
            return reply;
        } catch (Exception e) {
            throw new RuntimeException("sendAndReceive() Failed!", e);
        }
    }

}
