package com.demo.spring.system.security.filter;

import com.demo.spring.system.security.account.Account;

public abstract class AbstractSecurityFilter implements SecurityFilter<Account> {

    @Override
    public Account doFilter(Account _account) {

        Account account = new Account();
        account.setCreated(_account.getCreated());
        account.setName(_account.getName());
        account.setPassword(_account.getPassword());

        return account;
    }
}
