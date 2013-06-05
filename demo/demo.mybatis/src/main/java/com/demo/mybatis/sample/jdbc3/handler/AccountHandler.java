package com.demo.mybatis.sample.jdbc3.handler;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.mybatis.sample.bean.Role;
import com.demo.mybatis.sample.bean.Account;

public class AccountHandler implements TypeHandler<Account> {

    @Override
    public Account handle(ResultSet rs) {

        Account account = null;

        try {
            if (rs.next()) {
                int _id = rs.getInt("id");
                String _name = rs.getString("name");
                String _password = rs.getString("password");
                int _role = rs.getInt("role");
                Date _created = rs.getDate("created");
                Date _lastLoginTime = rs.getDate("last_login_time");

                account = new Account();
                account.setId(_id);
                account.setName(_name);
                account.setPassword(_password);
                account.setRole(Role.codeOf(_role));
                account.setCreated(_created);
                account.setLastLoginTime(new java.util.Date(_lastLoginTime.getTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return account;
    }
}
