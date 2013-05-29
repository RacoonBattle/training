package com.demo.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.util.StringUtils;

public class MethodLogAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        String value = method.getAnnotation(MethodLog.class).value();

        if (StringUtils.hasText(value)) {
            System.out.println(value + " Begin.");
        } else {
            System.out.println(value + " Begin.");
        }
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println(method.getName() + " End.");
    }
}
