package com.demo.mybatis.sample.mybatis1_xml;

import java.util.LinkedHashMap;
import java.util.Map;

import com.demo.mybatis.sample.bean.Account;

// 			/ Provider
// DAO 		
//			/ Accessor
// Service 	/ Manager
// 			/ 
public class AccountAccessorImpl extends AbstractAccessor implements AccountAccessor {

	@Override
	public Account getAccount(String name) {

		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("name", name);

		// business logic
		return sqlSession.selectOne("account.select", params);
	}

	@Override
	public Account getAccount(int id) {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("id", id);

		return sqlSession.selectOne("account.select", params);
	}
}
