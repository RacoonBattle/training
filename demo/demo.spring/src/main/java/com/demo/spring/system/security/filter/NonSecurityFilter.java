package com.demo.spring.system.security.filter;

import org.springframework.stereotype.Service;

import com.demo.spring.system.security.account.Account;

@Service("nonSecurityFilter")
public class NonSecurityFilter extends AbstractSecurityFilter {

    @Override
    public Account doFilter(Account account) {
        return super.doFilter(account);
    }
}
