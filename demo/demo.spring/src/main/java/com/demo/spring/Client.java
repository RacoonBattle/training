package com.demo.spring;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.demo.spring.system.security.account.Account;
import com.demo.spring.system.security.account.AccountAccessor;
import com.demo.spring.system.security.account.MappedAccountAccessor;

public class Client {

    public static void main(String[] args) {

        AccountAccessor accessor = buildAccountAccessor();

        System.out.println(accessor.getAccount("alice"));
    }

    private static AccountAccessor buildAccountAccessor() {

        Account alice = new Account();
        alice.setName("alice");
        alice.setCreated(new Date());

        Account bob = new Account();
        bob.setName("bob");
        bob.setCreated(new Date());

        Map<String, Account> accountMap = new HashMap<String, Account>();
        accountMap.put("alice", alice);
        accountMap.put("bob", bob);

        MappedAccountAccessor accessor = new MappedAccountAccessor();
        accessor.setAccountMap(accountMap);

        return accessor;
    }
}
