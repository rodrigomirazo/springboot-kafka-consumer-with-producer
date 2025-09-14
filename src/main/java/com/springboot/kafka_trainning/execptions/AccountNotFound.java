package com.springboot.kafka_trainning.execptions;

import lombok.Getter;

@Getter
public class AccountNotFound extends RuntimeException {

    private final String ownerName;

    public AccountNotFound(String ownerName) {
        this.ownerName = ownerName;
    }

}
