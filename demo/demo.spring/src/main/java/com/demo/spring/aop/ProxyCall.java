package com.demo.spring.aop;

import java.lang.reflect.Proxy;

public class ProxyCall {

    public static void main(String[] args) {

        final Hello hello = new HelloImpl();

        Hello helloProxy = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(), new Class<?>[] { Hello.class },
                new ProxyInvocationHandler(hello));

        helloProxy.sayHello("alice");
    }
}
