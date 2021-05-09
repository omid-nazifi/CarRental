package at.wiencampus.se.customerservice.configuration;

import at.wiencampus.se.common.dto.CustomerServiceReply;
import at.wiencampus.se.common.dto.CustomerServiceRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
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

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfiguration {

    @Value("${kafka.broker1}")
    private String KAFKA_BROKER;
    @Value("${kafka.customer.groupid}")
    private String GROUP_ID;

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
    public CustomerServiceReply loginListener(CustomerServiceRequest customerServiceRequest) {
        //TODO service
        return null;
    }

    @KafkaListener(topics = "${kafka.customer.topic.register.request}", containerFactory = "requestListenerContainerFactory")
    @SendTo()
    public CustomerServiceReply registerListener(CustomerServiceRequest customerServiceRequest) {
        //TODO service
        return null;
    }

    @KafkaListener(topics = "${kafka.customer.topic.get_all.request}", containerFactory = "requestListenerContainerFactory")
    @SendTo()
    public CustomerServiceReply getAllListener(CustomerServiceRequest customerServiceRequest) {
        //TODO service
        return null;
    }
}
