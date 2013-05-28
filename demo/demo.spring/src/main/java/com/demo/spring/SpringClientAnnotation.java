package com.demo.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.spring.system.security.account.AccountAccessor;

public class SpringClientAnnotation {

    public static void main(String[] args) {

        ApplicationContext context = buildBeanFactory();
        AccountAccessor accessor = context.getBean("mappedAccountAccessor", AccountAccessor.class);

        System.out.println(accessor.getAccount("alice"));
    }

    static ApplicationContext buildBeanFactory() {
        return new ClassPathXmlApplicationContext("spring/service-annotation.xml");
    }
}
