package com.demo.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAOPConfig {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/aop.xml");

        Hello hello = context.getBean(Hello.class);
        hello.sayHello("alice");
    }
}
