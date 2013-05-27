package com.demo.mybatis.sample.jdbc4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.mybatis.sample.bean.User;

public class Client {

	public static void main(String[] args) {
		
		 SqlSession session = new DefaultSqlSession();

		 // User user = session.selectOne("select id, name from users where name=?", "frank");
				 
		 Map<String, Object> param = new HashMap<String, Object>();
		 param.put("name", "tom"); 		 
		 // param.put("id", 1);

		 
		 User user = session.selectOne("user.select",
				 User.class,
				 param);
		 
		 
		 List<User> users = session.selectList(
				 "select id, name from users",
				 User.class);
		 
		 List<User> users2 = session.selectList(
				 "select id, name from users where birth=?",
				 User.class,
				 "2010-01-01");
	}
}
