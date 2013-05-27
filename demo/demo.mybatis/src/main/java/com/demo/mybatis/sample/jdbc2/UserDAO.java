package com.demo.mybatis.sample.jdbc2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.mybatis.sample.bean.Role;
import com.demo.mybatis.sample.bean.User;
import com.demo.mybatis.sample.jdbc1.SQLHelper;

public class UserDAO {

    public User getUserByName(String name) throws SQLException {

        // 参数检查
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        User user = null;

        try {
            // 获取连接
            connection = SQLHelper.getConnection(); // <= static resource access

            // 查询准备
            statement = connection.prepareStatement(
            		"select id, name, role, last_login_time" +
            		" from users" +
            		" where" +
            		" name=?");

            // 绑定参数
            statement.setString(1, name);

            // 执行查询
            rs = statement.executeQuery();

            // 绑定结果
            if (rs.next()) {
                int _id = rs.getInt("id");
                String _name = rs.getString("name");
                int _role = rs.getInt("role");
                Date _lastLoginTime = rs.getDate("last_login_time");

                user = new User();
                user.setId(_id);
                user.setName(_name);
                user.setRole(Role.codeOf(_role));
                user.setLastLoginTime(new java.util.Date(_lastLoginTime.getTime()));
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

        return user;
    }
}
