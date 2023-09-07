package com.ewallet.springbootewallet.repository;

import com.ewallet.springbootewallet.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionDao extends JpaRepository<Transaction, Long> {
    Transaction findByTid(Long tid);

    List<Transaction> findByFromAccountId(Long fromAccountId);

    List<Transaction> findByToAccountId(Long toAccountId);

    // List<Transaction> findByFromAccountIdOrderByTime(long id);
    // List<Transaction> findByToAccountIdOrderByTime(long id);
}
