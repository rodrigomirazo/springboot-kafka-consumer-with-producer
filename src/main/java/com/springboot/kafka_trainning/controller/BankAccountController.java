package com.springboot.kafka_trainning.controller;

import com.springboot.kafka_trainning.dtos.BankAccountDTO;
import com.springboot.kafka_trainning.entity.BankAccountEntity;
import com.springboot.kafka_trainning.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank/account")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/byOwner/{owner}")
    public ResponseEntity<BankAccountDTO> getByOwner(@PathVariable("owner") String owner) {

        BankAccountDTO bankAccountDTO = bankAccountService.getByOwner(owner);
        return ResponseEntity.ok().body(bankAccountDTO);
    }

    @PostMapping()
    public ResponseEntity<BankAccountEntity> upsertBankAccount(@RequestBody BankAccountEntity bankAccountEntity) {

        BankAccountEntity savedEntity = bankAccountService.save(bankAccountEntity);

        return ResponseEntity.ok().body(savedEntity);
    }
}
