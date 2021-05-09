package at.wiencampus.se.vehicleservice.configuration;

import at.wiencampus.se.common.dto.CurrencyConvertReply;
import at.wiencampus.se.common.dto.CurrencyConvertRequest;
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
public class CurrencyConverterKafkaConfiguration {

    @Value("${kafka.broker1}")
    private String KAFKA_BROKER;
    @Value("${kafka.customer.groupid}")
    private String GROUP_ID;
    @Value("${kafka.topic.currency.convert.reply}")
    public String REPLY_TOPIC_CONVERT;

    @Bean
    public Map<String, Object> currencyConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return props;
    }

    @Bean
    public Map<String, Object> currencyProducerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ReplyingKafkaTemplate<String, CurrencyConvertRequest, CurrencyConvertReply> currencyReplyKafkaTemplate(ProducerFactory<String,
            CurrencyConvertRequest> pf, KafkaMessageListenerContainer<String, CurrencyConvertReply> lc) {
        return new ReplyingKafkaTemplate<>(pf, lc);
    }

    @Bean
    public ProducerFactory<String, CurrencyConvertRequest> currencyRequestProducerFactory() {
        return new DefaultKafkaProducerFactory<>(currencyProducerConfigs(), new StringSerializer(), new JsonSerializer<CurrencyConvertRequest>());
    }

    @Bean
    public ConsumerFactory<String, CurrencyConvertReply> currencyReplyConsumerFactory() {
        JsonDeserializer<CurrencyConvertReply> replyJsonDeserializer = new JsonDeserializer<>();
        replyJsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory(currencyConsumerConfigs(), new StringDeserializer(), replyJsonDeserializer);
    }

    @Bean
    public KafkaMessageListenerContainer<String, CurrencyConvertReply> currencyListenerContainer() {
        ContainerProperties containerProperties = new ContainerProperties(REPLY_TOPIC_CONVERT);
        return new KafkaMessageListenerContainer<>(currencyReplyConsumerFactory(), containerProperties);
    }

}