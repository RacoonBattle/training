package com.demo.mybatis.sample.jdbc4;

import java.util.List;
import java.util.Map;

public interface SqlSession {

	// <T> T selectOne(String sql, Class<T> clazz, Object... params);
	<T> T selectOne(String sqlId, Class<T> clazz, Map<String, Object> params);
	
	/*
	 * 1, (sql, params) => statement
	 * 
	 * 2, (clazz) => typehandler
	 * 3, typehandler.handler(rs)
	 * 4, return t
	 */

	<E> List<E> selectList(String sql, Class<E> clazz, Object... params);
	
	int update(String sql, Object... params);
}
