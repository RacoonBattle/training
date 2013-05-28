package com.demo.spring;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.demo.spring.system.security.account.AccountAccessor;

public class SpringClient {

    public static void main(String[] args) {

        DefaultListableBeanFactory factory = buildBeanFactory();
        AccountAccessor accessor = factory.getBean(AccountAccessor.class);

        System.out.println(accessor.getAccount("alice"));

        factory.destroySingletons();
    }

    static DefaultListableBeanFactory buildBeanFactory() {
        Resource config = new ClassPathResource("spring/service.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinitions(config);

        return factory;
    }
}
