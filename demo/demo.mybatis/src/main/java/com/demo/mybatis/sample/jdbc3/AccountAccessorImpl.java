package com.demo.mybatis.sample.jdbc3;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.demo.mybatis.sample.bean.Account;
import com.demo.mybatis.sample.jdbc3.handler.TypeHandler;

public class AccountAccessorImpl extends AbstractAccessor implements AccountAccessor {

    // immutable things here
    private static final String SQL_SELECT_ACCOUNT_BY_NAME = "select id, name, password, role, created, last_login_time from account where name=?";

    @Override
    public Account getAccountByName(String name) {

        Account account = null;

        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = buildPreparedStatement(SQL_SELECT_ACCOUNT_BY_NAME, name);

            rs = statement.executeQuery();

            TypeHandler<Account> handler = getTypeHandler(Account.class);

            account = handler.handle(rs);
        } catch (Exception e) {
            e.printStackTrace();
            // logger
            // close
            // TODO: handle exception
        } finally {
            // TODO: release resources
        }

        return account;
        // return
        // getTypeHandler(Account.class)
        //    .handle(
        //        buildPreparedStatement(
        //            SQL_SELECT_ACCOUNT_BY_NAME,
        //            name).executeQuery()
        //    );
        
    }
}
