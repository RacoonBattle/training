package com.demo.mybatis.sample.jdbc1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.demo.mybatis.sample.bean.Role;
import com.demo.mybatis.sample.bean.Account;
import com.demo.mybatis.sample.util.SQLHelper;

public class AccountAccessor {

    public Account getAccountByName(String name) throws SQLException {

        // 参数检查
        if (name == null || name.isEmpty()) {
            return null; // TODO throw IllegalArgumentException
        }

        // 获取连接
        Connection connection = SQLHelper.getConnection();  // <= static resource access

        // 查询准备
        Statement statement = connection.createStatement(); // SQLException

        // 绑定参数
        String sql = "select" +
                " id," +
                " name," +
                " password," +
                " role," +
                " created," +
                " last_login_time" +
                " from account" +
                " where name='" + name + "'"; // bug: SQL注入

        // 执行查询
        ResultSet rs = statement.executeQuery(sql);

        // 绑定结果
        Account account = null;
        if (rs.next()) {
            int _id = rs.getInt("id");
            String _name = rs.getString("name");
            String _password = rs.getString("password");
            int _role = rs.getInt("role");
            Date _created = rs.getDate("created");
            Date _lastLoginTime = rs.getDate("last_login_time"); // (diff java.sql.Date java.util.Date)

            account = new Account();
            account.setId(_id);
            account.setName(_name);
            account.setPassword(_password);
            account.setRole(Role.codeOf(_role));
            account.setCreated(_created);
            account.setLastLoginTime(new java.util.Date(_lastLoginTime.getTime()));
        } else {
            // 
                // ? return null;
                // return 1/2//3
                                // 
                // throw AccountNotFoundException(name);
                // throw IllegalArgumetn(name);
        }

        // finally - 释放资源
        // try - finally
        rs.close();
        statement.close();
        connection.close();

        return account;
    }

    public static void main(String[] args) throws SQLException {

        AccountAccessor accessor = new AccountAccessor();

        Account account = accessor.getAccountByName("alice");

        System.out.println(account);
    }
}
