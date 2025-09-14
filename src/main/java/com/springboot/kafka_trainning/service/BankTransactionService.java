package com.springboot.kafka_trainning.service;

import com.springboot.kafka_trainning.entity.BankTransactionEntity;
import com.springboot.kafka_trainning.dtos.BankTransactionDTO;
import com.springboot.kafka_trainning.repository.BankTransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Service
public class BankTransactionService {

    @Autowired
    private BankTransactionRepository repository;

    @Transactional(
            transactionManager = "transactionManager",
            propagation = Propagation.REQUIRED,    // join or create transaction
            isolation = Isolation.REPEATABLE_READ, // ensure balance consistency
            rollbackFor = RuntimeException.class
    )
    public void persistIsolatedTransaction(BankTransactionDTO bankTransactionDTO) {

        BankTransactionEntity transaction = repository.findById(UUID.fromString(bankTransactionDTO.getId()))
                .orElseThrow(() -> new RuntimeException("Account not found: " + UUID.fromString(bankTransactionDTO.getId())));

        // Remove Taxes, let's say 10%
        transaction.setAmount(transaction.getAmount() * 0.90 );

        // debit
        repository.save(transaction);

        // simulate an error
        if (transaction.getAmount() > 500) {
            throw new RuntimeException("Transfer limit exceeded!");
        }
    }
}
