package com.springboot.kafka_trainning.ingest;

import com.springboot.kafka_trainning.dtos.BankTransactionDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${app.topic.name:json-topic}")
    private String topicName;

    public ProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional(transactionManager = "kafkaTransactionManager")
    public void sendMessage(BankTransactionDTO message) {
        kafkaTemplate.executeInTransaction(kt -> {
            kt.send(topicName, message.getId(), message);
            return true;
        });
    }
}
