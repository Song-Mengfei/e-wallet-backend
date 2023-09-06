package com.ewallet.springbootewallet.service.serviceImpl;

import com.ewallet.springbootewallet.domain.Transaction;
import com.ewallet.springbootewallet.repository.TransactionDao;
import com.ewallet.springbootewallet.service.TransactionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Resource
    private TransactionDao transactionDao;

    @Override
    public Boolean transactService(Transaction transaction) {
        //TODO
        return true;
    }


}
