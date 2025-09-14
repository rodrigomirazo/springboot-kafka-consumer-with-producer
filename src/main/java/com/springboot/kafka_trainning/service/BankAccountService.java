package com.springboot.kafka_trainning.service;

import com.springboot.kafka_trainning.dtos.BankAccountDTO;
import com.springboot.kafka_trainning.entity.BankAccountEntity;
import com.springboot.kafka_trainning.execptions.AccountNotFound;
import com.springboot.kafka_trainning.execptions.BalanceExceeded;
import com.springboot.kafka_trainning.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    //Get by id
    public BankAccountDTO getByOwner(String owner) throws AccountNotFound {

        Optional<BankAccountEntity> result = bankAccountRepository.findByOwner(owner);
        if(result.isPresent()) {
            BankAccountEntity entity = result.get();
            return new BankAccountDTO(entity.getOwner(), entity.getBalance());
        }
        throw new AccountNotFound("Account not found");
    }

    // Upsert Bank Account
    @Transactional(
            transactionManager = "transactionManager",
            rollbackFor = BalanceExceeded.class
    )
    public BankAccountEntity save(BankAccountEntity bankAccount) throws BalanceExceeded {

        if (bankAccount.getBalance() > 500.00D) {
            throw new BalanceExceeded(bankAccount.getBalance());
        }

        return bankAccountRepository.save(bankAccount);
    }
}
