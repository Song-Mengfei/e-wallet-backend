package com.ewallet.springbootewallet.service.serviceImpl;

import com.ewallet.springbootewallet.domain.Account;
import com.ewallet.springbootewallet.domain.Transaction;
import com.ewallet.springbootewallet.repository.AccountDao;
import com.ewallet.springbootewallet.repository.UserDao;
import com.ewallet.springbootewallet.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    @Resource
    private UserDao userDao;

    @Override
    public Account createAccountService(Account account) {
        // FIXME: ADD check if account already exist.
        Account newAccount = accountDao.save(account);
        if (newAccount != null) {
            account.setAccountPassword("");
        }
        return newAccount;
    }

    @Override
    public Account findAccountByUidService(Long uid) {
        return null;
    }

    @Override
    public Account findAccountByAidService(Long aid) {
        return null;
    }

    @Override
    public Integer transferOutService(Long aid, String accountPassword, Double amount) {
        // FIXME: Change return null to throw exception.
        Account account = accountDao.findAccountByAid(aid);
        if (account == null) {
            return null;
        }
        Double currentBalance = account.getBalance();
        if (amount < 0) {
            return null;
        }
        if (!account.getAccountPassword().equals(accountPassword)) {
            // password not right
            return null;
        }
        if (amount <= currentBalance) {
            // sufficient balance in account, can proceed
            Double newBalance = currentBalance - amount;
            Integer status = accountDao.updateBalance(aid, account.getAid(), newBalance);
            return status;
        } else {
            return null;
        }
    }

    @Override
    public Integer receiveService(Long aid, Double amount) {
        Account account = accountDao.findAccountByAid(aid);
        if (account == null) {
            return null;
        }
        Double newBalance = account.getBalance() + amount;
        Integer status =accountDao.updateBalance(aid, account.getAid(), newBalance);
        return status;
    }

    @Override
    public Transaction transferToOneService(Long aid, Long receiverAid, String password, Double amount) {
        // FIXME: Need to check if aid not equal to receiverAid, if equal, through error.
        // FIXME: Check if aid/receiverAid already in database, if not, through error.
        if (aid.equals(receiverAid)) {
            // perform top-up
            receiveService(receiverAid, amount);
        } else {
            // perform transaction
            transferOutService(aid, password, amount);
            receiveService(receiverAid, amount);
        }
        // Add transaction history
        Transaction transaction = Transaction.build(aid, receiverAid, "", amount, "", "", "");
        return transaction;
    }
}
