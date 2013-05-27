package com.demo.mybatis.sample.mybatis1_xml;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.demo.mybatis.sample.bean.User;

public class Client {

	public static void main(String[] args) throws IOException {

		UserAccessor accessor = getUserAccessor();
		
		//User user = accessor.getUser("frank");
		User user = accessor.getUser(1);
		
		System.out.println(user);
	}

	static UserAccessor getUserAccessor() throws IOException {

		String config = "mybatis-config-standalone.xml";
		InputStream inputStream = Resources.getResourceAsStream(config);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		UserAccessorImpl accessor = new UserAccessorImpl();
		accessor.setSqlSession(sessionFactory.openSession());
		// TODO session.close();

		return accessor;
	}
}
