package com.demo.mybatis.sample.jdbc3.handler;

import java.lang.reflect.Field;
import java.sql.ResultSet;

public class SimpleBeanHandler<T> implements TypeHandler<T> {

    private Class<T> clazz = null;
    private Field[] fields = null;

    public SimpleBeanHandler(Class<T> clazz) {
        this.clazz = clazz;
        this.fields = parseFields(clazz); // execute only once when initialization.
    }

    @Override
    public T handle(ResultSet resultSet) {

        T object = null;

        try {
            object = clazz.newInstance();
            for (Field field : fields) {
                // String name = field.getName();
                // field.getType();
                // Integer/String/Date
            	
            	    // id <int>      <= getInt()
            	    	// name <String> <= getString("name");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return object;
    }

    public Field[] parseFields(Class<T> clazz) {
        // TODO: parse
        return null;
    }
}
