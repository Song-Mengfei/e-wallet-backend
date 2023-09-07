package com.ewallet.springbootewallet.service;

import com.ewallet.springbootewallet.domain.Account;
import com.ewallet.springbootewallet.domain.Transaction;

public interface AccountService {
    Account createAccountService(Account account);

    Account findAccountByUidService(Long uid);

    Account findAccountByAidService(Long aid);

    Integer transferOutService(Long uid, String password, Double amount) throws Exception;

    Integer receiveService(Long uid, Double amount) throws Exception;

    Transaction transferToOneService(Long aid, Long receiverAid, String password, Double amount);
}
