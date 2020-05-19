package com.assignment.transaction.service;


import com.assignment.transaction.constants.TransactionAccount;
import com.assignment.transaction.model.TransactionModel;

import java.util.List;

public interface TransactionService {
    public List<TransactionModel> getAllTransaction();

    public List<TransactionModel> getTransactionByTransactionAccount(TransactionAccount transactionAccount);

    public List<TransactionModel> getTransactionByDescription(String transactionDescription);

    List<TransactionModel> getTransactionByTransactionAccountAndDescription(TransactionAccount transactionAccount, String transactionDescription);
}
