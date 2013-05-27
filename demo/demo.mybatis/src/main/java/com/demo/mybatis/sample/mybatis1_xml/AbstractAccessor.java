package com.demo.mybatis.sample.mybatis1_xml;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractAccessor {

	// logger
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
}
