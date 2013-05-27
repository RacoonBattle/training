package com.demo.mybatis.sample.mybatis1_xml;

import com.demo.mybatis.sample.bean.User;

// OrderAccessor

public interface UserAccessor {
	
	User getUser(String name);
	
	User getUser(int id);
}
