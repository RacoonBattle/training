package com.demo.mybatis.sample.mybatis2_spring;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.mybatis.sample.bean.Role;
import com.demo.mybatis.sample.bean.User;

public class Insert {

	public static void main(String[] args) {

		// Initialize mybatis with Spring
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/dao.xml");
		SqlSessionFactory sessionFactory = context.getBean("sqlSessionFactory", SqlSessionFactory.class);

		// TODO Open session
		SqlSession session = sessionFactory.openSession();

		// Build query parameter
		User user = new User();
		user.setName("frank3");
		user.setRole(Role.MANAGER);

		session.update("user.insert", user);

		// Close session
		session.close();
	}
}
