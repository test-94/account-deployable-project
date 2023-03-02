package com.synechron.enbdaccountcrud.service;


import com.synechron.enbd.creditcard.enbdcreditcard.schema.SchemaAccount;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "javaguides_json", groupId = "myGroup")
    public void consume(ConsumerRecord<String, SchemaAccount> deserializedRecord){
        String key = deserializedRecord.key();
//        SchemaAccount account = deserializedRecord.value();
        LOGGER.info(String.format("Value is : %s ", deserializedRecord.value()));
        LOGGER.info(String.format("Data received from kafka ->  %s ", key));
    }

}
