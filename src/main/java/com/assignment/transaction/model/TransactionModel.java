package com.assignment.transaction.model;

import com.assignment.transaction.constants.TransactionAccount;
import com.assignment.transaction.constants.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TransactionModel {

    private Long transactionId;

    private Long transactionAmount;

    private TransactionAccount transactionAccount;

    private TransactionType transactionType;

    private LocalDate transactionDate;

    private String transactionDescription;
}
