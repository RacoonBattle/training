package com.demo.spring.security.account;

import java.util.HashMap;
import java.util.Map;

public class MappedAccountAccessor extends AbstractAccountAccessor {

    private Map<String, Account> accountMap;

    @Override
    public Account getAccount(String name) {
        Account _account = accountMap.get(name);

        Account account = new Account();
        account.setName(_account.getName());
        account.setCreated(_account.getCreated());

        return account;
    }

    public void init() {

        logger.debug("init Begin.");

        if (accountMap == null) {
            accountMap = new HashMap<String, Account>();
        }

        logger.debug("init End.");
    }

    public void dispose() {

        logger.debug("init Begin.");

        accountMap = null;

        logger.debug("init End.");
    }

    public void setAccountMap(Map<String, Account> accountMap) {
        this.accountMap = accountMap;
    }

}
