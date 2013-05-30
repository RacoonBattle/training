package com.demo.spring.system.security.account;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.spring.system.security.filter.SecurityFilter;

public abstract class AbstractAccountAccessor implements AccountAccessor {

    protected Logger logger = LoggerFactory.getLogger(MappedAccountAccessor.class);

    @Resource(name="securityFilter")
    protected SecurityFilter<Account> securityFilter;

    public void init() {

        logger.debug("init Begin.");

        doInit();

        logger.debug("init End.");
    }

    public void dispose() {

        logger.debug("init Begin.");

        doDispose();

        logger.debug("init End.");
    }

    protected abstract void doInit();

    protected abstract void doDispose();

    public void setSecurityFilter(SecurityFilter<Account> securityFilter) {
        this.securityFilter = securityFilter;
    }

    protected Account doSecurityFilter(Account account) {
        return securityFilter == null ? account : securityFilter.doFilter(account);
    }
}
