package com.springboot.kafka_trainning.controller;

import com.springboot.kafka_trainning.ingest.ProducerService;
import com.springboot.kafka_trainning.dtos.BankTransactionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank/account")
public class BankTransferController {

    @Autowired
    private ProducerService producerService;

    @PostMapping("/sendMoney")
    public ResponseEntity<String> publishMessage(@RequestBody BankTransactionDTO bankTransactionDTO) {
        producerService.sendMessage(bankTransactionDTO);
        return ResponseEntity.ok("Message Sent with id: " + bankTransactionDTO.getId());
    }
}
