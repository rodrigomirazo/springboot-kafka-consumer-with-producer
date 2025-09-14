package com.springboot.kafka_trainning.ingest;

import com.springboot.kafka_trainning.dtos.BankTransactionDTO;
import com.springboot.kafka_trainning.service.BankTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService {

    @Autowired
    private BankTransactionService bankTransactionService;

    @KafkaListener(topics = "json-topic", groupId = "json-consumer-group")
    public void consume(BankTransactionDTO bankTransactionDTO) {
        System.out.println("Received DTO: " + bankTransactionDTO.toString());

        bankTransactionService.persistIsolatedTransaction(bankTransactionDTO);
    }
}
