package com.demo.mybatis.sample.jdbc3;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.demo.mybatis.sample.bean.User;
import com.demo.mybatis.sample.jdbc3.handler.TypeHandler;
import com.demo.mybatis.sample.jdbc3.handler.UserHandler;
import com.demo.mybatis.sample.jdbc3.pool.ConnectionPool;
import com.demo.mybatis.sample.jdbc3.pool.ResourcePool;

public class Client {

	public static void main(String[] args) {

		UserDAO dao = getUserDAO();

		User user = dao.getUserByName("alice");

		System.out.println(user);
	}

	static UserDAO getUserDAO() {

		ResourcePool<Connection> pool = new ConnectionPool();

		Map<Class<?>, TypeHandler<?>> converterMap = new HashMap<Class<?>, TypeHandler<?>>();
		converterMap.put(User.class, new UserHandler());
		// converterMap.put(User.class, new BeanConverter<User>(User.class));

		UserDAOImpl dao = new UserDAOImpl();
		dao.setConverterMap(converterMap);
		dao.setPool(pool);

		return dao;
	}
}
