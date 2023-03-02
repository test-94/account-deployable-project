package com.synechron.enbdaccountcrud.kafkaconfig;

import com.synechron.enbd.creditcard.enbdcreditcard.schema.SchemaAccount;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic javaGuidesTopic() {
        return TopicBuilder.name("javaguides").build();
    }

    @Bean
    public NewTopic javaGuidesJsonTopic() {
        return TopicBuilder.name("javaguides_json").build();
    }

    @Bean
    public NewTopic CreditCardTopic() {
        return TopicBuilder.name("credit-card-topic").build();
    }

//    @Bean
//    public ConsumerFactory<String, SchemaAccount> consumerFactory(KafkaProperties kafkaProperties) {
//        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
//    }
//
//    @Bean
//    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, SchemaAccount>> kafkaListenerContainerFactory(KafkaProperties kafkaProperties) {
//        ConcurrentKafkaListenerContainerFactory<String, SchemaAccount> factory = new ConcurrentKafkaListenerContainerFactory<String, SchemaAccount>();
//        factory.setConsumerFactory(consumerFactory(kafkaProperties));
//        return factory;
//    }

}
