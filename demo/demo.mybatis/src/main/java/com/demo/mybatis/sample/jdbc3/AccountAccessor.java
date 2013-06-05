package com.demo.mybatis.sample.jdbc3;

import com.demo.mybatis.sample.bean.Account;

public interface AccountAccessor {

    Account getAccountByName(String name);
}
