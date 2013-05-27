package com.demo.mybatis.sample.jdbc1;

import java.sql.SQLException;

import com.demo.mybatis.sample.bean.User;

public class Client {

	public static void main(String[] args) throws SQLException {

		UserDAO dao = new UserDAO();

		User user = dao.getUserByName("tom");

		System.out.println(user);
	}
}
