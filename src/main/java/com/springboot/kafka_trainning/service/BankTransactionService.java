package com.springboot.kafka_trainning.service;

import com.springboot.kafka_trainning.entity.BankAccountEntity;
import com.springboot.kafka_trainning.entity.BankTransactionEntity;
import com.springboot.kafka_trainning.dtos.BankTransactionDTO;
import com.springboot.kafka_trainning.execptions.AccountNotFound;
import com.springboot.kafka_trainning.repository.BankAccountRepository;
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

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Transactional(
            transactionManager = "transactionManager",
            propagation = Propagation.REQUIRED,    // join or create transaction
            isolation = Isolation.REPEATABLE_READ, // ensure balance consistency
            rollbackFor = AccountNotFound.class
    )
    public void persistIsolatedTransaction(BankTransactionDTO bankTransactionDTO) {

        BankAccountEntity transaction = bankAccountRepository.findByOwner(bankTransactionDTO.getAccountOwner())
                .orElseThrow(() -> new AccountNotFound(bankTransactionDTO.getAccountOwner()));

        // Remove Taxes, let's say 10%
        bankTransactionDTO.setAmount(bankTransactionDTO.getAmount() * 0.90 );

        // debit
        BankTransactionEntity entity = new BankTransactionEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setAmount(bankTransactionDTO.getAmount() * 0.90);
        entity.setAccountOwner(bankTransactionDTO.getAccountOwner());
        repository.save(entity);

        // simulate an error
        if (bankTransactionDTO.getAmount() > 500) {
            throw new RuntimeException("Transfer limit exceeded!");
        }
    }
}
