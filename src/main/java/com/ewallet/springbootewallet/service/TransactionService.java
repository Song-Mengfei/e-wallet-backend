package com.ewallet.springbootewallet.service;

import com.ewallet.springbootewallet.domain.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> findByFromAccountIdService(Long fromAccountId);

    List<Transaction> findByToAccountIdService(Long toAccountId);

    Transaction addOneTransactionService(Transaction transaction);



}
