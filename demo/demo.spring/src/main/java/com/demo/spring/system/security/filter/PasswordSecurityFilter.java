package com.demo.spring.system.security.filter;

import org.springframework.stereotype.Component;

import com.demo.spring.system.security.account.Account;

@Component("securityFilter")
public class PasswordSecurityFilter implements SecurityFilter<Account> {

    public PasswordSecurityFilter() {
        System.out.println("password filter init");
    }

    @Override
    public Account doFilter(Account account) {

        account.setPassword("******");

        return account;
    }
}
