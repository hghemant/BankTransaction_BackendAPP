package com.assignment.transaction.service.impl;

import com.assignment.transaction.constants.TransactionAccount;
import com.assignment.transaction.model.TransactionModel;
import com.assignment.transaction.repository.TransactionRepository;
import com.assignment.transaction.repository.entity.TransactionEntity;
import com.assignment.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <code>TransactionServiceImpl.java</code>
 * Service class responsible for the transactions
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<TransactionModel> getAllTransaction() {
        return transactionRepository.findAllByOrderByTransactionDateDesc()
                .stream()
                .map(this::convertEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionModel> getTransactionByTransactionAccount(TransactionAccount transactionAccount) {
        return transactionRepository.findByTransactionAccount(transactionAccount)
                .stream()
                .map(this::convertEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionModel> getTransactionByDescription(String transactionDescription) {
        return transactionRepository.findByTransactionDescriptionContainingIgnoreCase(transactionDescription)
                .stream()
                .map(this::convertEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionModel> getTransactionByTransactionAccountAndDescription
            (TransactionAccount transactionAccount, String transactionDescription) {
        return transactionRepository.findByTransactionAccountAndTransactionDescriptionContainingIgnoreCase
                (transactionAccount, transactionDescription)
                .stream()
                .map(this::convertEntityToModel)
                .collect(Collectors.toList());
    }

    private TransactionModel convertEntityToModel(TransactionEntity transactionEntity) {
        return new TransactionModel(transactionEntity.getTransactionId(),
                transactionEntity.getTransactionAmount().longValue(),
                transactionEntity.getTransactionAccount(),
                transactionEntity.getTransactionType(),
                transactionEntity.getTransactionDate(),
                transactionEntity.getTransactionDescription());
    }
}
