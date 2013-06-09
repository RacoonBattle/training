package com.demo.spring.system.security.account;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DBAccountAccessor extends AbstractAccountAccessor {

    @Override
    public Account getAccount(String name) {
        return null;
    }

    @Transactional
    public void updateAccount(Account account) {

        // update account

        // network broken

        // accountLogAccessor.insert(accountLog);
    }

    public void batchInsert(List<Account> accounts) {

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
