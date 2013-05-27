package com.demo.mybatis.sample.jdbc4;

import java.util.List;
import java.util.Map;

public class DefaultSqlSession implements SqlSession {

	public DefaultSqlSession() {
		// initialize connection pool 		<=  DBCP/c3p0...
		// initialize type handler			
	}

	// @Override
	// public <T> T selectOne(String sql, Class<T> clazz, Object... params) {
	// }

	@Override
	public <T> T selectOne(String sqlId, Class<T> clazz, Map<String, Object> params) {

		// 1. Open connection 			<= Connection pool

		// 2. Prepare statement 			<= PreparedStatement (maybe cached to reuse)

		// 3. Execute SQL with parameter	<= Database

		// 4. Get appropriate type handler  <= TypeHandler

		// 5. Handle result set				=> handle(result set, class type) => Object/List
		
		// 6. Release connection etc.
		
		// 7. Return result of step 5

		return null;
	}

	@Override
	public <E> List<E> selectList(String sql, Class<E> clazz, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(String sql, Object... params) {
		// TODO Auto-generated method stub
		return 0;
	}
}
