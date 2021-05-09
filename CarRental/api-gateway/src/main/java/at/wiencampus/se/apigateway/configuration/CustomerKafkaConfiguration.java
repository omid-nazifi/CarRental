package at.wiencampus.se.apigateway.configuration;

import at.wiencampus.se.common.dto.CustomerServiceReply;
import at.wiencampus.se.common.dto.CustomerServiceRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CustomerKafkaConfiguration {

    @Value("${kafka.broker1}")
    private String KAFKA_BROKER;
    @Value("${kafka.customer.groupid}")
    private String GROUP_ID;
    @Value("${kafka.customer.topic.login.reply}")
    public String REPLY_TOPIC_LOGIN;
    @Value("${kafka.customer.topic.register.reply}")
    public String REPLY_TOPIC_REGISTER;
    @Value("${kafka.customer.topic.get_all.reply}")
    public String REPLY_TOPIC_GET_ALL;

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
    public ReplyingKafkaTemplate<String, CustomerServiceRequest, CustomerServiceReply> replyKafkaTemplate(ProducerFactory<String,
            CustomerServiceRequest> pf, KafkaMessageListenerContainer<String, CustomerServiceReply> lc) {
        return new ReplyingKafkaTemplate<>(pf, lc);
    }

    @Bean
    public ProducerFactory<String, CustomerServiceRequest> requestProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(), new StringSerializer(), new JsonSerializer<CustomerServiceRequest>());
    }

    @Bean
    public ConsumerFactory<String, CustomerServiceReply> replyConsumerFactory() {
        JsonDeserializer<CustomerServiceReply> replyJsonDeserializer = new JsonDeserializer<>();
        replyJsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory(consumerConfigs(), new StringDeserializer(), replyJsonDeserializer);
    }

    @Bean
    public KafkaMessageListenerContainer<String, CustomerServiceReply> listenerContainer() {
        ContainerProperties containerProperties = new ContainerProperties(REPLY_TOPIC_LOGIN, REPLY_TOPIC_REGISTER, REPLY_TOPIC_GET_ALL);
        return new KafkaMessageListenerContainer<>(replyConsumerFactory(), containerProperties);
    }

}