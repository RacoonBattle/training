package com.demo.mybatis.sample.mybatis2_spring;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.mybatis.sample.bean.Account;

public class Client {

	public static void main(String[] args) {

		// Initialize mybatis with Spring
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/dao.xml", "spring/service.xml");
		SqlSessionFactory sessionFactory = context.getBean("sqlSessionFactory", SqlSessionFactory.class);

		// TODO Open session
		SqlSession session = sessionFactory.openSession();

		// Build query parameter
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("name", "frank");

		Account user = session.selectOne("account.select", params);
		System.out.println(user);

		// Close session
		session.close();


		//ModelAccessor<User> userAccessor = context.getBean("userAccessor", ModelAccessor.class);
		//System.out.println(userAccessor.select(new GenericQuery().fill("name", "frank")));
	}
}
