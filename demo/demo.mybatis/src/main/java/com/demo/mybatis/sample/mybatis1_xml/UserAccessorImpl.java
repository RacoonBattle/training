package com.demo.mybatis.sample.mybatis1_xml;

import java.util.LinkedHashMap;
import java.util.Map;

import com.demo.mybatis.sample.bean.User;

// 			/ Provider
// DAO 		
//			/ Accessor
// Service 	/ Manager
// 			/ 
public class UserAccessorImpl extends AbstractAccessor implements UserAccessor {

	@Override
	public User getUser(String name) {

		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("name", name);

		// business logic
		return sqlSession.selectOne("user.select", params);
	}

	@Override
	public User getUser(int id) {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("id", id);

		return sqlSession.selectOne("user.select", params);
	}
}
