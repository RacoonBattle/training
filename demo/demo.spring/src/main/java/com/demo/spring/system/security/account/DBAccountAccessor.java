package com.demo.spring.system.security.account;

import org.springframework.stereotype.Service;

@Service
public class DBAccountAccessor extends AbstractAccountAccessor {

    @Override
    public Account getAccount(String name) {
        return null;
    }

    @Override
    protected void doInit() {
        // TODO Auto-generated method stub
    }

    @Override
    protected void doDispose() {
        // TODO Auto-generated method stub
    }
}
