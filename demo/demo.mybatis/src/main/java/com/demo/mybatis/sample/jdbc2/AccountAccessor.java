package com.demo.mybatis.sample.jdbc2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.mybatis.sample.bean.Role;
import com.demo.mybatis.sample.bean.Account;
import com.demo.mybatis.sample.util.SQLHelper;

public class AccountAccessor {

    public Account getAccountByName(String name) throws SQLException {

        // 参数检查
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Account account = null;

        try {
            // 获取连接
            connection = SQLHelper.getConnection(); // <= static resource access

            String sql = "select" +
                    " id," +
                    " name," +
                    " password," +
                    " role," +
                    " created," +
                    " last_login_time" +
                    " from account" +
                    " where name=?"; // bug: SQL注入
            
            // 查询准备
            statement = connection.prepareStatement(sql);

            // 绑定参数
            statement.setString(1, name);

            // 执行查询
            rs = statement.executeQuery();

            // 绑定结果
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
                return null;
            }
        } finally {
            // 释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // logger
                    // TODO: handle exception
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // TODO: handle exception
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO: handle exception
                }
            }
        }

        return account;
    }

    public static void main(String[] args) throws SQLException {

        AccountAccessor dao = new AccountAccessor();

        Account account = dao.getAccountByName("frank");

        System.out.println(account);
    }
}
