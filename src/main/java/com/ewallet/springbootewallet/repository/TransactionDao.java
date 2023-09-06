package com.ewallet.springbootewallet.repository;

import com.ewallet.springbootewallet.domain.Transaction;
import com.ewallet.springbootewallet.service.TransactionService;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionDao extends JpaRepository<Transaction, Long> {
    Transaction findByTid(Long tid);

    List<Transaction> findByFromAccountId(Long fromAccountId);

    List<Transaction> findByToAccountId(Long toAccountId);

    //TODO: check
    // List<Transaction> findByFromAccountIdOrToAccountIdOrderByTime(long id);
}
