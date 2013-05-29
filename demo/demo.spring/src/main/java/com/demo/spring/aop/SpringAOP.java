package com.demo.spring.aop;

import org.springframework.aop.framework.ProxyFactory;


public class SpringAOP {

    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory(new HelloImpl());
        factory.addInterface(Hello.class);

        MethodLogAdvice advice = new MethodLogAdvice();

        // method advice
        factory.addAdvice(advice);

        Hello hello = (Hello) factory.getProxy();
        hello.sayHello("alice");
    }
}
