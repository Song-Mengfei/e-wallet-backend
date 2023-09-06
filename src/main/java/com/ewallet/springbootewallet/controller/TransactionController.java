package com.ewallet.springbootewallet.controller;

import com.ewallet.springbootewallet.domain.Transaction;
import com.ewallet.springbootewallet.service.TransactionService;
import com.ewallet.springbootewallet.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Resource
    private TransactionService transactionService;
    @PostMapping("/transact")
    public Result<Transaction> TransactController(@RequestBody Transaction newTransaction) {
        Boolean success = transactionService.transactService(newTransaction);
        if (success) {
            return Result.success(newTransaction, "Transaction success");
        } else {
            return Result.error("3", "Transaction failed");
        }
    }

    @PostMapping("/findTransactionByFromId")
    public Result<List<Transaction>> findTransactionByFromIdController(@RequestParam Long fromAccountId) {
        List<Transaction> transactionsOut = transactionService.findByFromAccountIdService(fromAccountId);
        if (transactionsOut.size() == 0) {
            return Result.error("1", "no transaction or user not exist");
        } else {
            return Result.success(transactionsOut, "out transaction find success");
        }
    }

    @PostMapping("/findTransactionByToId")
    public Result<List<Transaction>> findTransactionByToIdController(@RequestParam Long toAccountId) {
        List<Transaction> transactionIn = transactionService.findByToAccountIdService(toAccountId);
        if (transactionIn.size() == 0) {
            return Result.error("1", "no transaction or user not exit");
        } else {
            return Result.success(transactionIn, "in transaction find success");
        }
    }

    @PostMapping("/addOneTransaction")
    public Result<Transaction> addOneTransaction(@RequestBody Transaction transaction) {
        Transaction addedTransaction = transactionService.addOneTransactionService(transaction);
        if (addedTransaction != null) {
            return Result.success(transaction, "transaction success");
        } else {
            return Result.error("1", "transaction failed");
        }
    }

}
