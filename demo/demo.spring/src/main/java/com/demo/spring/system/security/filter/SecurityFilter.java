package com.demo.spring.system.security.filter;

public interface SecurityFilter<T> {

    T doFilter(T object);
}
