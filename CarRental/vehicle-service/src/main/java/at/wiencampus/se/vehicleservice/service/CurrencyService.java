package at.wiencampus.se.vehicleservice.service;

import at.wiencampus.se.common.dto.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Service
public class CurrencyService extends WebServiceGatewaySupport {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyService.class);

    @Autowired
    private ReplyingKafkaTemplate<String, CurrencyConvertRequest, CurrencyConvertReply> requestReplyKafkaTemplate;

    @Value("${kafka.topic.currency.convert.request}")
    public String REQUEST_TOPIC_CURRENCY;
    @Value("${kafka.topic.currency.convert.reply}")
    public String REPLY_TOPIC_CURRENCY;

    public CurrencyConvertReply getCurrency(double dollar, CurrencyEnum currency) {
        CurrencyConvertRequest request = new CurrencyConvertRequest();
        request.setDollar(dollar);
        request.setCurrency(currency);

        logger.info("Requesting information for " + dollar + currency);

        //call MService
        CurrencyConvertReply reply = sendAndReceive(request, REQUEST_TOPIC_CURRENCY, REPLY_TOPIC_CURRENCY);
        return reply;
    }

    private CurrencyConvertReply sendAndReceive(CurrencyConvertRequest request, String requestTopic, String replyTopic) {
        try {
            ProducerRecord<String, CurrencyConvertRequest> record = new ProducerRecord<String, CurrencyConvertRequest>(requestTopic, request);
            record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, replyTopic.getBytes()));
            RequestReplyFuture<String, CurrencyConvertRequest, CurrencyConvertReply> sendAndReceive = requestReplyKafkaTemplate.sendAndReceive(record);

            ConsumerRecord<String, CurrencyConvertReply> consumerRecord = sendAndReceive.get();
            CurrencyConvertReply reply = consumerRecord.value();
            if(reply.getException() != null) {
                throw new RuntimeException("Exception in the CurrencyService!", reply.getException());
            }
            return reply;
        } catch (Exception e) {
            throw new RuntimeException("sendAndReceive() Failed!", e);
        }
    }
}
