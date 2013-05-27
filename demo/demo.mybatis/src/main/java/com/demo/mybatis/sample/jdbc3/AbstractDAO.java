package com.demo.mybatis.sample.jdbc3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;

import com.demo.mybatis.sample.jdbc3.handler.TypeHandler;
import com.demo.mybatis.sample.jdbc3.pool.ResourcePool;

public abstract class AbstractDAO {

	protected Logger logger = Logger.getLogger(getClass().getName());

	private ResourcePool<Connection> pool = null;

	// ResultSet => User
	private Map<Class<?>, TypeHandler<?>> typeHandlerMap;

	protected PreparedStatement buildPreparedStatement(String sql) throws SQLException {
		return buildPreparedStatement(sql, (Object[]) null);
	}

	protected PreparedStatement buildPreparedStatement(String sql, Object... params) throws SQLException {

		Connection connection = pool.getResource();

		PreparedStatement statement = connection.prepareStatement(sql);

		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				Object param = params[i];

				if (param instanceof String) {
					statement.setString(i + 1, (String) param);

					// TODO other types
				} else {
					statement.setObject(i + 1, param);
				}
			}
		}

		return statement;
	}

	@SuppressWarnings("unchecked")
	protected <T> TypeHandler<T> getTypeHandler(Class<T> clazz) {

		return (TypeHandler<T>) typeHandlerMap.get(clazz);
	}

	public void setPool(ResourcePool<Connection> pool) {
		this.pool = pool;
	}

	public void setConverterMap(Map<Class<?>, TypeHandler<?>> converterMap) {
		this.typeHandlerMap = converterMap;
	}
}
