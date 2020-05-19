package com.assignment.transaction.service.impl;

import com.assignment.transaction.constants.TransactionAccount;
import com.assignment.transaction.model.TransactionModel;
import com.assignment.transaction.repository.TransactionRepository;
import com.assignment.transaction.repository.entity.TransactionEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * <code>TransactionServiceImplTest.java</code>
 * test for Service class responsible for the transactions
 */
@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {

    @Mock
    TransactionRepository transactionRepository;

    TransactionServiceImpl victim;

    @Before
    public void setUp() {
        victim = new TransactionServiceImpl(transactionRepository);
    }

    @Test
    public void testGetTransactionByTransactionAccountShouldReturnTransactions() {
        //setup
        TransactionEntity mockTransaction = mock(TransactionEntity.class);
        List<TransactionEntity> mockList = new ArrayList<>();
        mockList.add(mockTransaction);
        mockList.add(mockTransaction);
        Mockito.when(mockTransaction.getTransactionAmount()).thenReturn(new BigDecimal(0));
        Mockito.when(transactionRepository.findByTransactionAccount(TransactionAccount.PAYMENTS)).thenReturn(mockList);
        //act
        List<TransactionModel> transactionModelList = victim.getTransactionByTransactionAccount(TransactionAccount.PAYMENTS);
        //verify
        Assert.assertEquals(2, transactionModelList.size());
    }

    @Test
    public void testGetTransactionByTransactionDescriptionShouldReturnTransactions() {
        //setup
        String description = "credit";
        TransactionEntity mockTransaction = mock(TransactionEntity.class);
        List<TransactionEntity> mockList = new ArrayList<>();
        mockList.add(mockTransaction);
        mockList.add(mockTransaction);
        Mockito.when(mockTransaction.getTransactionAmount()).thenReturn(new BigDecimal(0));
        Mockito.when(transactionRepository.findByTransactionDescriptionContainingIgnoreCase(description)).thenReturn(mockList);
        //act
        List<TransactionModel> transactionModelList = victim.getTransactionByDescription(description);
        //verify
        Assert.assertEquals(2, transactionModelList.size());
    }

    @Test
    public void testGetAllTransactionsShouldReturnTransactions() {
        //setup
        TransactionEntity mockTransaction = mock(TransactionEntity.class);
        List<TransactionEntity> mockList = new ArrayList<>();
        mockList.add(mockTransaction);
        mockList.add(mockTransaction);
        Mockito.when(mockTransaction.getTransactionAmount()).thenReturn(new BigDecimal(0));
        Mockito.when(transactionRepository.findAllByOrderByTransactionDateDesc()).thenReturn(mockList);
        //act
        List<TransactionModel> transactionModelList = victim.getAllTransaction();
        //verify
        Assert.assertEquals(2, transactionModelList.size());
    }

    @Test
    public void testGetTransactionByTransactionAccountAndDescriptionShouldReturnTransactions() {
        //setup
        String description = "credit";
        TransactionEntity mockTransaction = mock(TransactionEntity.class);
        List<TransactionEntity> mockList = new ArrayList<>();
        mockList.add(mockTransaction);
        mockList.add(mockTransaction);
        Mockito.when(mockTransaction.getTransactionAmount()).thenReturn(new BigDecimal(0));
        Mockito.when(transactionRepository.findByTransactionAccountAndTransactionDescriptionContainingIgnoreCase(TransactionAccount.PAYMENTS,
                description )).thenReturn(mockList);
        //act
        List<TransactionModel> transactionModelList = victim.getTransactionByTransactionAccountAndDescription(
                TransactionAccount.PAYMENTS, description);
        //verify
        Assert.assertEquals(2, transactionModelList.size());
    }
}
