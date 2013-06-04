package com.demo.dao;

import java.util.List;

public interface QueryDAO {

    <T> List<T> executeForObjectList(String sqlId, Object param);

    <T> List<T> executeForObjectList(String sqlId, Object param, Integer startIndex, Integer maxCount);

    int executeForObject(String sqlId, Object param, Class<Integer> clazz);
}
