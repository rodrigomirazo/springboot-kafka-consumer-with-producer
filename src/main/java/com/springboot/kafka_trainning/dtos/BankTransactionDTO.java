package com.springboot.kafka_trainning.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BankTransactionDTO {
    private String id;
    private String accountOwner;
    private Double amount;
}
