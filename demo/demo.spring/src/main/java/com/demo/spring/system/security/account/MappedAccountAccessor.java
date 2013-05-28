package com.demo.spring.system.security.account;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class MappedAccountAccessor extends AbstractAccountAccessor {

    @Resource
    private Map<String, Account> accountMap;

    @Override
    public Account getAccount(String name) {
        Account _account = accountMap.get(name);

        Account account = new Account();
        account.setName(_account.getName());
        account.setPassword(_account.getPassword());
        account.setCreated(_account.getCreated());

        return super.doSecurityFilter(account);
    }

    public void setAccountMap(Map<String, Account> accountMap) {
        this.accountMap = accountMap;
    }

    public void doInit() {

        if (accountMap == null) {
            accountMap = new HashMap<String, Account>();
        }
    }

    public void doDispose() {
        accountMap = null;
    }
}
