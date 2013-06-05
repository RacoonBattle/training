package com.demo.mybatis.sample.mybatis1_xml;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.demo.mybatis.sample.bean.Account;

public class Client {

    public static void main(String[] args) throws IOException {

        AccountAccessor accessor = getUserAccessor();

        // Account account = accessor.getAccount("frank");
        Account account = accessor.getAccount(1);

        System.out.println(account);
    }

    static AccountAccessor getUserAccessor() throws IOException {

        String config = "mybatis-config-standalone.xml";
        InputStream inputStream = Resources.getResourceAsStream(config);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        AccountAccessorImpl accessor = new AccountAccessorImpl();
        accessor.setSqlSession(sessionFactory.openSession());
        // TODO session.close();

        return accessor;
    }
}
