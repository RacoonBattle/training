package com.demo.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.demo.spring.security.account.AccountAccessor;

public class SpringClient {

    public static void main(String[] args) {

        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("spring/service.xml"));

        AccountAccessor accessor = factory.getBean(AccountAccessor.class);

        System.out.println(accessor.getAccount("alice"));

        factory.destroySingletons();
    }
}
