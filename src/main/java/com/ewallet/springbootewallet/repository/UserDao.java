package com.ewallet.springbootewallet.repository;

import com.ewallet.springbootewallet.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUname(String uname);
    User findByUnameAndPassword(String uname, String password);
    User findByUid(Long uid);
    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET balance=:balance WHERE uname=:uname AND password=:password", nativeQuery = true)
    Integer updateBalance(@Param("uname") String uname, @Param("password") String password, @Param("balance") Double amount);
    // save(object o) has been implemented by jpa
}
