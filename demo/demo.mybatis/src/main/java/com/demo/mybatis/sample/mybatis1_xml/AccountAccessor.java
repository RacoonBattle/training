package com.demo.mybatis.sample.mybatis1_xml;

import com.demo.mybatis.sample.bean.Account;

// OrderAccessor

public interface AccountAccessor {

    Account getAccount(String name);

    Account getAccount(int id);
}
