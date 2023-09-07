package com.ewallet.springbootewallet.controller;

import com.ewallet.springbootewallet.domain.Account;
import com.ewallet.springbootewallet.domain.Transaction;
import com.ewallet.springbootewallet.service.AccountService;
import com.ewallet.springbootewallet.service.TransactionService;
import com.ewallet.springbootewallet.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @Resource
    private TransactionService transactionService;

    @PostMapping("/createAccount")
    public Result<Account> createAccount(@RequestBody Account account) {
        Account newAccount = accountService.createAccountService(account);
        if (newAccount != null) {
            return Result.success(newAccount, "create account success");
        } else {
            return Result.error("1", "Account Already exist");
        }
    }

    @PostMapping("/topup")
    public Result<Transaction> topUpController(@RequestParam Long aid, @RequestParam String accountPassword ,@RequestParam Double amount) {
        Transaction transactionRecord = accountService.transferToOneService(aid, aid, accountPassword, amount);
        return Result.success(transactionRecord, "money top-up success");
//        if (user != null) {
//            return Result.success(user, "top up success");
//        } else {
//            return Result.error("4", "top up fail");
//        }
    }

    @PostMapping("/transactionToOne")
    public Result<Transaction> transactionToOneController(@RequestParam Long aid, @RequestParam Long receiverAid, @RequestParam String accountPassword, @RequestParam Double amount) {
        Transaction transactionRecord = accountService.transferToOneService(aid, receiverAid, accountPassword, amount);
        Transaction addedTransaction = transactionService.addOneTransactionService((transactionRecord));
        return Result.success(addedTransaction, "transaction success");
    }
}
