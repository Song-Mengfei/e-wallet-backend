package com.ewallet.springbootewallet.service;

import com.ewallet.springbootewallet.domain.User;

public interface UserService {
    /**
     * Login service
     * @param uname
     * @param password
     * @return
     */
    User loginService(String uname, String password);

    /**
     * Register service
     * @param user
     * @return
     */
    User registerService(User user);

    //TODO
//    User updateService(User user);
}
