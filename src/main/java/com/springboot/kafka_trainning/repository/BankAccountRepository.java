package com.springboot.kafka_trainning.repository;

import com.springboot.kafka_trainning.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface BankAccountRepository extends JpaRepository<BankAccountEntity, UUID> {

    @Query("""
                select bankAccount FROM BankAccountEntity bankAccount where bankAccount.owner = :owner
            """)
    Optional<BankAccountEntity> findByOwner(@Param("owner") String owner);
}
