package com.demo.mybatis.sample.mybatis3_mapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.demo.mybatis.sample.mybatis1_xml.AccountAccessor;

public class Client {

    public static void main(String[] args) {

        AccountAccessor accessor = (AccountAccessor) getMapper(AccountAccessor.class);

        accessor.getAccount(1);
    }

    static Object getMapper(final Class<?>... clazz) {

        return Proxy.newProxyInstance(clazz[0].getClassLoader(), clazz, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println(clazz[0].getName());
                System.out.println(method.getName()); // => sqlId :)

                for (Object arg : args) {
                    System.out.println(arg);
                }

                return null;
            }
        });
    }
}
