package com.springboot.kafka_trainning.execptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BalanceExceeded extends RuntimeException {
    private final Double balance;
}
