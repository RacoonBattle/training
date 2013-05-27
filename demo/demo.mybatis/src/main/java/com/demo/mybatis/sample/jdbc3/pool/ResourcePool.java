package com.demo.mybatis.sample.jdbc3.pool;


/**
 * 连接池，实现思路参见 http://www.javaworld.com/javatips/jw-javatip78.html
 */
public interface ResourcePool<T> {

    T getResource();

    void releaseResource(T resource);
}
