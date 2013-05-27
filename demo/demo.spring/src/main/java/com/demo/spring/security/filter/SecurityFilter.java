package com.demo.spring.security.filter;

public interface SecurityFilter<T> {

    T doFilter(T object);
}
