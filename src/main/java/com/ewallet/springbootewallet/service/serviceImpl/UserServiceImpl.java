package com.ewallet.springbootewallet.service.serviceImpl;

import com.ewallet.springbootewallet.domain.User;
import com.ewallet.springbootewallet.repository.UserDao;
import com.ewallet.springbootewallet.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
