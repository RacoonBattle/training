package com.demo.mybatis.sample.jdbc1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.demo.mybatis.sample.bean.Role;
import com.demo.mybatis.sample.bean.User;

public class UserDAO {

    public User getUserByName(String name) throws SQLException {

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
        		" role," +
        		" last_login_time" +
        		" from users" +
        		" where name='" + name + "'"; // bug: SQL注入

        // 执行查询
        ResultSet rs = statement.executeQuery(sql);

        // 绑定结果
        User user = null;
        if (rs.next()) {
            int _id = rs.getInt("id");
            String _name = rs.getString("name");
            int _role = rs.getInt("role");
            Date _lastLoginTime = rs.getDate("last_login_time"); // (diff java.sql.Date java.util.Date)

            user = new User();
            user.setId(_id);
            user.setName(_name);
            user.setRole(Role.codeOf(_role));
            user.setLastLoginTime(new java.util.Date(_lastLoginTime.getTime()));
        } else {
            // 
        		// ? return null;
        		// return 1/2//3
	        		        	// 
        		// throw UserNotFoundException(name);
	        	// throw IllegalArgumetn(name);
        }

        // finally - 释放资源
        // try - finally
        rs.close();
        statement.close();
        connection.close();
        
        return user;
    }
}
