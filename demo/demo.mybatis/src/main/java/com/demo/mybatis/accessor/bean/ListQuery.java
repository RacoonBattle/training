package com.demo.mybatis.accessor.bean;

public interface ListQuery extends Query {

    /**
     * 获取查询起始点
     * 
     * @return
     */
    Integer getStartIndex();

    /**
     * 获取查询结果条数限制
     * 
     * @return
     */
    Integer getMaxCount();
}
