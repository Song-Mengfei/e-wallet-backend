package com.ewallet.springbootewallet.service.serviceImpl;

import com.ewallet.springbootewallet.domain.Transaction;
import com.ewallet.springbootewallet.domain.User;
import com.ewallet.springbootewallet.repository.UserDao;
import com.ewallet.springbootewallet.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User loginService(String uname, String password) {
        User user = userDao.findByUnameAndPassword(uname, password);
        if (user != null) {
            user.setPassword("");
        }
        return user;
    }

    @Override
    public User registerService(User user) {
        if (userDao.findByUname(user.getUname()) != null) {
            return null; // user already exist
        } else {
            User newUser = userDao.save(user);
            if (newUser != null) {
                newUser.setPassword("");
            }
            return newUser;
        }
    }

    @Override
    public Integer transferOutService(String uname, Double amount) {
        User user = userDao.findByUname(uname);
        if (user == null) {
            return null;
        }
        Double currentBalance = user.getBalance();
        if (amount < 0) {
            return null;
        }
        if (amount <= currentBalance) {
            // sufficient balance in account, can proceed
            Double newBalance = currentBalance - amount;
            Integer status = userDao.updateBalance(user.getUname(), user.getPassword(), newBalance);
            return status;
        } else {
            return null;
        }
    }

    @Override
    public Integer receiveService(String uname, Double amount) {
        User user = userDao.findByUname(uname);
        if (user == null) {
            return null;
        }
        Double newBalance = user.getBalance() + amount;
        Integer status = userDao.updateBalance(user.getUname(), user.getPassword(), newBalance);
        return status;
    }

    @Override
    public Transaction transferToOneService(String uname, String receiverUname, Double amount) {
        if (uname == receiverUname) {
            // perform top-up
            receiveService(receiverUname, amount);
        } else {
            // perform transaction
            transferOutService(uname, amount);
            receiveService(receiverUname, amount);
        }
        // Add transaction history
        Transaction transaction = Transaction.build(userDao.findByUname(uname).getUid(), userDao.findByUname(receiverUname).getUid(), "", amount, "", "", "");
        return transaction;
    }
}
