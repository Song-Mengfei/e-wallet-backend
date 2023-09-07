package com.ewallet.springbootewallet.service.serviceImpl;

import com.ewallet.springbootewallet.Exceptions.AccountNotFoundException;
import com.ewallet.springbootewallet.Exceptions.InsufficientAuthenticationException;
import com.ewallet.springbootewallet.Exceptions.TransactionBadRequest;
import com.ewallet.springbootewallet.domain.Account;
import com.ewallet.springbootewallet.domain.Transaction;
import com.ewallet.springbootewallet.repository.AccountDao;
import com.ewallet.springbootewallet.repository.UserDao;
import com.ewallet.springbootewallet.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.LocalTime;

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    @Resource
    private UserDao userDao;

    @Override
    public Account createAccountService(Account account) {
        Account existedAccount = findAccountByUidService(account.getUid());
        if (existedAccount != null) {
            return null;
        }
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

    @Transactional
    @Override
    public Integer transferOutService(Long aid, String accountPassword, Double amount) throws Exception{
        Account account = accountDao.findAccountByAid(aid);
        if (account == null) {
            throw new AccountNotFoundException(aid);
        }
        Double currentBalance = account.getBalance();
        //TODO: also add in frontend validation
        if (amount < 0) {
            throw new TransactionBadRequest();
        }
        if (!account.getAccountPassword().equals(accountPassword)) {
            // password not right
            throw new InsufficientAuthenticationException();
        }
        if (amount <= currentBalance) {
            // sufficient balance in account, can proceed
            Double newBalance = currentBalance - amount;
            Integer status = accountDao.updateBalance(aid, newBalance);
            return status;
        } else {
            throw new TransactionBadRequest();
        }
    }

    @Override
    public Integer receiveService(Long aid, Double amount) throws Exception{
        Account account = accountDao.findAccountByAid(aid);
        if (account == null) {
            throw new AccountNotFoundException(aid);
        }
        Double newBalance = account.getBalance() + amount;
        Integer status =accountDao.updateBalance(aid, newBalance);
        return status;
    }

    @Transactional
    @Override
    public Transaction transferToOneService(Long aid, Long receiverAid, String password, Double amount) {
        try {
            if (aid.equals(receiverAid)) {
            // perform top-up
                receiveService(receiverAid, amount);
            } else {
            // perform transaction
                transferOutService(aid, password, amount);
                receiveService(receiverAid, amount);
            }
            // Add transaction history
            Transaction transaction = Transaction.build(aid, receiverAid, "", amount, "", "", LocalTime.now().toString() );
            return transaction;
        }
        catch (Exception e){
            return null;
        }
    }
}
