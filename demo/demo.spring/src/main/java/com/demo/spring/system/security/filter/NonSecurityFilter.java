package com.demo.spring.system.security.filter;

import com.demo.spring.system.security.account.Account;

public class NonSecurityFilter extends AbstractSecurityFilter {

    @Override
    public Account doFilter(Account account) {
        return account;
    }
}
