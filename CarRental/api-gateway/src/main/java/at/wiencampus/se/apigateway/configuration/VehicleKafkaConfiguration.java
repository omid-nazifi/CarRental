package at.wiencampus.se.apigateway.configuration;

import at.wiencampus.se.common.dto.VehicleServiceReply;
import at.wiencampus.se.common.dto.VehicleServiceRequest;
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
public class VehicleKafkaConfiguration {

    @Value("${kafka.broker1}")
    private String KAFKA_BROKER;
    @Value("${kafka.customer.groupid}")
    private String GROUP_ID;
    @Value("${kafka.vehicle.topic.book.reply}")
    public String REPLY_TOPIC_BOOK;
    @Value("${kafka.vehicle.topic.return.reply}")
    public String REPLY_TOPIC_RETURN;
    @Value("${kafka.vehicle.topic.all.reply}")
    public String REPLY_TOPIC_ALL_VEHICLE;
    @Value("${kafka.vehicle.topic.all_with_currency.reply}")
    public String REPLY_TOPIC_ALL_VEHICLE_WITH_CURRENCY;
    @Value("${kafka.vehicle.topic.all_customer_rentals.reply}")
    public String REPLY_TOPIC_ALL_CUSTOMER_RENTALS;
    @Value("${kafka.vehicle.topic.new.reply}")
    public String REPLY_TOPIC_NEW_VEHICLE;

    @Bean
    public Map<String, Object> vehicleConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return props;
    }

    @Bean
    public Map<String, Object> vehicleProducerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ReplyingKafkaTemplate<String, VehicleServiceRequest, VehicleServiceReply> vehicleReplyKafkaTemplate(ProducerFactory<String,
            VehicleServiceRequest> pf, KafkaMessageListenerContainer<String, VehicleServiceReply> lc) {
        return new ReplyingKafkaTemplate<>(pf, lc);
    }

    @Bean
    public ProducerFactory<String, VehicleServiceRequest> vehicleRequestProducerFactory() {
        return new DefaultKafkaProducerFactory<>(vehicleProducerConfigs(), new StringSerializer(), new JsonSerializer<VehicleServiceRequest>());
    }

    @Bean
    public ConsumerFactory<String, VehicleServiceReply> vehicleReplyConsumerFactory() {
        JsonDeserializer<VehicleServiceReply> replyJsonDeserializer = new JsonDeserializer<>();
        replyJsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory(vehicleConsumerConfigs(), new StringDeserializer(), replyJsonDeserializer);
    }

    @Bean
    public KafkaMessageListenerContainer<String, VehicleServiceReply> vehicleListenerContainer() {
        ContainerProperties containerProperties = new ContainerProperties(REPLY_TOPIC_BOOK, REPLY_TOPIC_RETURN, REPLY_TOPIC_NEW_VEHICLE, REPLY_TOPIC_ALL_VEHICLE, REPLY_TOPIC_ALL_CUSTOMER_RENTALS, REPLY_TOPIC_ALL_VEHICLE_WITH_CURRENCY);
        return new KafkaMessageListenerContainer<>(vehicleReplyConsumerFactory(), containerProperties);
    }

}