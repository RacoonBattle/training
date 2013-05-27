package com.demo.spring.security.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.spring.security.filter.SecurityFilter;

public abstract class AbstractAccountAccessor implements AccountAccessor {

    protected Logger logger = LoggerFactory.getLogger(MappedAccountAccessor.class);

    protected SecurityFilter<String> passwordFilter;

    public void setPasswordFilter(SecurityFilter<String> passwordFilter) {
        this.passwordFilter = passwordFilter;
    }
}
