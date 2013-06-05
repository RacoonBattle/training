package com.demo.mybatis.sample.jdbc4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.mybatis.sample.bean.Account;

public class Client {

	public static void main(String[] args) {
		
		 SqlSession session = new DefaultSqlSession();

		 // User user = session.selectOne("select id, name from users where name=?", "frank");
				 
		 Map<String, Object> param = new HashMap<String, Object>();
		 param.put("name", "tom"); 		 
		 // param.put("id", 1);

		 
		 Account user = session.selectOne("user.select",
				 Account.class,
				 param);
		 
		 
		 List<Account> users = session.selectList(
				 "select id, name from users",
				 Account.class);
		 
		 List<Account> users2 = session.selectList(
				 "select id, name from users where birth=?",
				 Account.class,
				 "2010-01-01");
	}
}
