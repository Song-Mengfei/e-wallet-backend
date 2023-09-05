package com.ewallet.springbootewallet.controller;

import com.ewallet.springbootewallet.domain.Transaction;
import com.ewallet.springbootewallet.service.TransactionService;
import com.ewallet.springbootewallet.utils.Result;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;

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



}
