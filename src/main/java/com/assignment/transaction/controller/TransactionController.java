package com.assignment.transaction.controller;

import com.assignment.transaction.constants.TransactionAccount;
import com.assignment.transaction.model.TransactionModel;
import com.assignment.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <code>TransactionController.java</code>
 * API class responsible for handling transaction
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    @ResponseBody
    public List<TransactionModel> getAllTransactions() {
        return transactionService.getAllTransaction();
    }

    @GetMapping(value = "", params = "transactionAccount")
    @ResponseBody
    public List<TransactionModel> getTransactionByTransactionAccount(@RequestParam TransactionAccount transactionAccount) {
        return transactionService.getTransactionByTransactionAccount(transactionAccount);
    }

    @GetMapping(value = "", params = "transactionDescription")
    @ResponseBody
    public List<TransactionModel> getTransactionByDescription(@RequestParam String transactionDescription) {
        return transactionService.getTransactionByDescription(transactionDescription);
    }

    @GetMapping(value = "", params = {"transactionDescription", "transactionAccount"})
    @ResponseBody
    public List<TransactionModel> getTransactionByTransactionAccountAndDescription(@RequestParam String transactionDescription,
                                                                                   @RequestParam TransactionAccount transactionAccount) {
        return transactionService.getTransactionByTransactionAccountAndDescription(transactionAccount, transactionDescription);
    }
}
