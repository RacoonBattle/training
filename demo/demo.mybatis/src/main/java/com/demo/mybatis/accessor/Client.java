package com.demo.mybatis.accessor;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.mybatis.accessor.bean.GenericQuery;
import com.demo.mybatis.accessor.bean.ListQuery;
import com.demo.mybatis.sample.bean.Account;


interface AccountDAO {
    
    List<Account> selectList();
}

public class Client {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/dao.xml");

        GenericModelAccessor<Account> accessor = new GenericModelAccessor<Account>("account");
        accessor.setSqlSession(context.getBean(SqlSession.class));

        ListQuery query = new GenericQuery();
        query.put("password", "123456");

        List<Account> list = accessor.select(query);

        for (Account account : list) {
            System.out.println(account);
        }
    }
}
