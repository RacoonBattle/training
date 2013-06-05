package com.demo.mybatis.accessor;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象模型数据访问
 * 
 * @author zhongyuan.zhang
 */
public abstract class AbstractModelAccessor {

    /** 日志 */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SqlSession sqlSession;

    @Resource
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
}
