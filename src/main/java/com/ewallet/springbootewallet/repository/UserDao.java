package com.ewallet.springbootewallet.repository;

import com.ewallet.springbootewallet.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUname(String uname);
    User findByUnameAndPassword(String uname, String password);
    // save(object o) has been implemented by jpa
}
