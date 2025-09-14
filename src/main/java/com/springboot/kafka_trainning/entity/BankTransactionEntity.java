package com.springboot.kafka_trainning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank_transaction")
public class BankTransactionEntity {

    @Id
    private UUID id;

    @Version
    private Long version;

    @Column(name = "account_owner")
    private String accountOwner;

    @Column(name = "amount")
    private Double amount;
}
