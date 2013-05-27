package com.demo.mybatis.sample.jdbc3.handler;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.mybatis.sample.bean.Role;
import com.demo.mybatis.sample.bean.User;

public class UserHandler implements TypeHandler<User> {

	@Override
	public User handle(ResultSet rs) {

		User user = null;

		try {
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return user;
	}

}
