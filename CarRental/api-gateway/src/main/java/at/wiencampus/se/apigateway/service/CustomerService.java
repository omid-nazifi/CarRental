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
public class CustomerService {

    @Autowired
    private ReplyingKafkaTemplate<String, CustomerServiceRequest, CustomerServiceReply> requestReplyKafkaTemplate;

    @Value("${kafka.customer.topic.login.request}")
    public String REQUEST_TOPIC_LOGIN;
    @Value("${kafka.customer.topic.login.reply}")
    public String REPLY_TOPIC_LOGIN;
    @Value("${kafka.customer.topic.register.request}")
    public String REQUEST_TOPIC_REGISTER;
    @Value("${kafka.customer.topic.register.reply}")
    public String REPLY_TOPIC_REGISTER;
    @Value("${kafka.customer.topic.get_all.request}")
    public String REQUEST_TOPIC_GET_ALL;
    @Value("${kafka.customer.topic.get_all.reply}")
    public String REPLY_TOPIC_GET_ALL;

    public CustomerData registerCustomer(CustomerData customer) {
        CustomerServiceRequest request = new CustomerServiceRequest();
        request.setName(CustomerServiceName.Register);
        request.setCustomer(customer);

        //call MService
        CustomerServiceReply reply = sendAndReceive(request, REQUEST_TOPIC_REGISTER, REPLY_TOPIC_REGISTER);
        if (reply.getName() == CustomerServiceName.Register) {
            return reply.getCustomerData();
        } else {
            throw new UnsupportedOperationException("Response Type is wrong! (" + reply.getName() + ")");
        }
    }

    public List<CustomerData> getCustomers() {
        CustomerServiceRequest request = new CustomerServiceRequest();
        request.setName(CustomerServiceName.GetAll);
        request.setLoginData(null);
        request.setCustomer(null);

        //call MService
        CustomerServiceReply reply = sendAndReceive(request, REQUEST_TOPIC_GET_ALL, REPLY_TOPIC_GET_ALL);
        if (reply.getName() == CustomerServiceName.GetAll) {
            return reply.getCustomers();
        }
        throw new UnsupportedOperationException("Response Type is wrong! (" + reply.getName() + ")");
    }

    public CustomerData loginUser(String email, String password) {
        LoginData login = new LoginData(email, password);
        CustomerServiceRequest request = new CustomerServiceRequest();
        request.setName(CustomerServiceName.Login);
        request.setLoginData(login);

        //call service
        CustomerServiceReply reply = sendAndReceive(request, REQUEST_TOPIC_LOGIN, REPLY_TOPIC_LOGIN);
        if (reply.getName() == CustomerServiceName.Login) {
            return reply.getCustomerData();
        }
        throw new UnsupportedOperationException("Response Type is wrong! (" + reply.getName() + ")");
    }

    private CustomerServiceReply sendAndReceive(CustomerServiceRequest request, String requestTopic, String replyTopic) {
        try {
            ProducerRecord<String, CustomerServiceRequest> record = new ProducerRecord<String, CustomerServiceRequest>(requestTopic, request);
            record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, replyTopic.getBytes()));
            RequestReplyFuture<String, CustomerServiceRequest, CustomerServiceReply> sendAndReceive = requestReplyKafkaTemplate.sendAndReceive(record);

            ConsumerRecord<String, CustomerServiceReply> consumerRecord = sendAndReceive.get();
            CustomerServiceReply reply = consumerRecord.value();
            return reply;
        } catch (Exception e) {
            throw new RuntimeException("sendAndReceive() Failed!", e);
        }
    }

}
