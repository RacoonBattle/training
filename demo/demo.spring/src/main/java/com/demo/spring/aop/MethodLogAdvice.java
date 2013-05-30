package com.demo.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.util.StringUtils;

public class MethodLogAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        MethodLog methodLog = method.getAnnotation(MethodLog.class);

        if (methodLog != null && StringUtils.hasText(methodLog.value())) {
            System.out.println(methodLog.value() + " Begin.");
        } else {
            System.out.println(method.getName() + " Begin.");
        }
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println(method.getName() + " End.");
    }
}
