package com.demo.mybatis.sample.jdbc3;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.demo.mybatis.sample.bean.User;
import com.demo.mybatis.sample.jdbc3.handler.TypeHandler;

public class UserDAOImpl extends AbstractDAO implements UserDAO {

	// immutable things here
	private static final String SQL_SELECT_USER_BY_NAME = "select id, name, role, last_login_time from users where name=?";

	@Override
	public User getUserByName(String name) {

		User user = null;

		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			statement = buildPreparedStatement(SQL_SELECT_USER_BY_NAME, name);

			rs = statement.executeQuery();

			TypeHandler<User> handler = getTypeHandler(User.class);

			user = handler.handle(rs);
		} catch (Exception e) {
			e.printStackTrace();
			// logger
			// close
			// TODO: handle exception
		} finally {
			// TODO: release resources
		}

		return user;
		// return
		// getTypeHandler(User.class)
		//    .handle(
		//        buildPreparedStatement(
		//            SQL_SELECT_USER_BY_NAME,
		//            name).executeQuery()
		//    );
		
	}

}
