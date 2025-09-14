package com.springboot.kafka_trainning.repository;

import com.springboot.kafka_trainning.entity.BankTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BankTransactionRepository extends JpaRepository<BankTransactionEntity, UUID> {


}
