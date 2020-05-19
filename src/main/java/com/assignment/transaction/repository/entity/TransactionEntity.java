package com.assignment.transaction.repository.entity;

import com.assignment.transaction.constants.TransactionAccount;
import com.assignment.transaction.constants.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "TRANSACTION_ENTITY")
@Getter
@Setter
public class TransactionEntity {

    @Id
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @Column(name = "TRANSACTION_AMOUNT", nullable = false)
    private BigDecimal transactionAmount;

    @Column(name = "TRANSACTION_ACCOUNT", nullable = false)
    private TransactionAccount transactionAccount;

    @Column(name = "TRANSACTION_TYPE", nullable = false)
    private TransactionType transactionType;

    @Column(name = "TRANSACTION_DATE", nullable = false)
    private LocalDate transactionDate;

    @Column(name = "TRANSACTION_DESCRIPTION", nullable = false)
    private String transactionDescription;

}
