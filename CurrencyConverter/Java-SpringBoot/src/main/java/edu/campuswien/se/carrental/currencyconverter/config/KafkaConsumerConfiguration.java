package edu.campuswien.se.carrental.currencyconverter.config;

import at.wiencampus.se.common.dto.CurrencyConvertReply;
import at.wiencampus.se.common.dto.CurrencyConvertRequest;
import edu.campuswien.se.carrental.currencyconverter.logic.Converter;
import edu.campuswien.se.carrental.currencyconverter.logic.ConverterImpl;
import edu.campuswien.se.carrental.currencyconverter.logic.ConverterProxy;
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

import java.util.*;

@Configuration
public class KafkaConsumerConfiguration {

    @Value("${kafka.broker1}")
    private String KAFKA_BROKER;
    @Value("${kafka.currency.groupid}")
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
    public ConsumerFactory<String, CurrencyConvertRequest> requestConsumerFactory() {
        JsonDeserializer<CurrencyConvertRequest> requestJsonDeserializer = new JsonDeserializer<>();
        requestJsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), requestJsonDeserializer);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, CurrencyConvertRequest>> requestListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CurrencyConvertRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(requestConsumerFactory());
        factory.setReplyTemplate(replyTemplate());
        return factory;
    }

    @Bean
    public KafkaTemplate<String, CurrencyConvertReply> replyTemplate() {
        return new KafkaTemplate<>(replyProducerFactory());
    }

    @Bean
    public ProducerFactory<String, CurrencyConvertReply> replyProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(), new StringSerializer(), new JsonSerializer<CurrencyConvertReply>());
    }

    @KafkaListener(topics = "${kafka.topic.currency.convert.request}", containerFactory = "requestListenerContainerFactory")
    @SendTo()
    public CurrencyConvertReply convertListener(CurrencyConvertRequest request) {
        CurrencyConvertReply reply = new CurrencyConvertReply();
        try {
            Converter converter = new ConverterImpl();
            Converter converterProxy = new ConverterProxy(converter);
            double convertedAmount = converterProxy.convertFromDollar(request.getCurrency(), request.getDollar());
            reply.setPrice(convertedAmount);
            reply.setCurrency(request.getCurrency());
        } catch (Exception e) {
            reply.setException(new Exception("Currency converter service failed!", e));
        }
        return reply;
    }

}
