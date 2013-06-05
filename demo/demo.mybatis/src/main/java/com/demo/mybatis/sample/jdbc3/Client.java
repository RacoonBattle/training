package com.demo.mybatis.sample.jdbc3;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.demo.mybatis.sample.bean.Account;
import com.demo.mybatis.sample.jdbc3.handler.TypeHandler;
import com.demo.mybatis.sample.jdbc3.handler.AccountHandler;
import com.demo.mybatis.sample.jdbc3.pool.ConnectionPool;
import com.demo.mybatis.sample.jdbc3.pool.ResourcePool;

public class Client {

	public static void main(String[] args) {

		AccountAccessor accessor = getUserDAO();

		Account user = accessor.getAccountByName("steve");

		System.out.println(user);
	}

	static AccountAccessor getUserDAO() {

		ResourcePool<Connection> pool = new ConnectionPool();

		Map<Class<?>, TypeHandler<?>> converterMap = new HashMap<Class<?>, TypeHandler<?>>();
		converterMap.put(Account.class, new AccountHandler());
		// converterMap.put(User.class, new BeanConverter<User>(User.class));

		AccountAccessorImpl accessor = new AccountAccessorImpl();
		accessor.setConverterMap(converterMap);
		accessor.setPool(pool);

		return accessor;
	}
}
