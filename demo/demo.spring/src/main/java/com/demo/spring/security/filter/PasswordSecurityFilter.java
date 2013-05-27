package com.demo.spring.security.filter;

public class PasswordSecurityFilter implements SecurityFilter<String> {

    @Override
    public String doFilter(String object) {
        return "******";
    }
}
