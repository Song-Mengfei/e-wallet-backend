package com.ewallet.springbootewallet.service.serviceImpl;

import com.ewallet.springbootewallet.domain.Transaction;
import com.ewallet.springbootewallet.repository.TransactionDao;
import com.ewallet.springbootewallet.repository.UserDao;
import com.ewallet.springbootewallet.service.TransactionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Resource
    private TransactionDao transactionDao;

    @Override
    public Boolean transactService(Transaction transaction) {
        //TODO
        return true;
    }

    @Override
    public List<Transaction> findByFromAccountIdService(Long fromAccountId) {
        List<Transaction> transactionsOut = transactionDao.findByFromAccountId(fromAccountId);
        return transactionsOut;
    }

    @Override
    public List<Transaction> findByToAccountIdService(Long toAccountId) {
        List<Transaction> transactionsIn = transactionDao.findByToAccountId(toAccountId);
        return transactionsIn;
    }

    @Override
    public Transaction addOneTransactionService(Transaction transaction) {
        Transaction addedTransaction = transactionDao.save(transaction);
        return addedTransaction;
    }




}
