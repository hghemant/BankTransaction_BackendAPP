package com.assignment.transaction.repository;

import com.assignment.transaction.constants.TransactionAccount;
import com.assignment.transaction.repository.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    public List<TransactionEntity> findAllByOrderByTransactionDateDesc();

    public List<TransactionEntity> findByTransactionAccount(TransactionAccount transactionAccount);

    public List<TransactionEntity> findByTransactionDescriptionContainingIgnoreCase(String transactionDescription);

    public List<TransactionEntity> findByTransactionAccountAndTransactionDescriptionContainingIgnoreCase
            (TransactionAccount transactionAccount, String transactionDescription);
}
