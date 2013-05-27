package com.demo.mybatis.sample.jdbc3.handler;

import java.sql.ResultSet;

public interface TypeHandler<T> {

    T handle(ResultSet resultSet);
}