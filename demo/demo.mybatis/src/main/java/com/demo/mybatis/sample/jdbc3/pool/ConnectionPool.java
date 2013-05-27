package com.demo.mybatis.sample.jdbc3.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool implements ResourcePool<Connection> {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Connection getResource() {

		// TODO Start a timer

		String url = "jdbc:mysql://localhost:3306/test?user=root&password=";

		try {
			return DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void releaseResource(Connection resource) {
		// TODO Auto-generated method stub
	}

}
