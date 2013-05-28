package com.demo.java.annotation;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class SpringAOP {

    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory(new HelloImpl());
        factory.addInterface(Hello.class);

        // method advice
        factory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println(method.getName() + " Begin.");
            }
        });
        factory.addAdvice(new AfterReturningAdvice() {

            @Override
            public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
                    throws Throwable {
                System.out.println(method.getName() + " End.");

            }
        });

        Hello hello = (Hello) factory.getProxy();
        hello.sayHello("alice");
    }
}
