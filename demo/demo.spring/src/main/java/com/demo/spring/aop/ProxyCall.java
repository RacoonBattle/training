package com.demo.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyCall {

    public static void main(String[] args) {

        final Hello hello = new HelloImpl();

        InvocationHandler handler = new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                boolean methodLogAnnotated = method.isAnnotationPresent(MethodLog.class);

                if (methodLogAnnotated) {
                    System.out.println(method.getName() + " Begin.");
                }

                hello.sayHello((String) args[0]);

                if (methodLogAnnotated) {
                    System.out.println(method.getName() + " End.");
                }
                return null;
            }
        };

        Hello helloProxy = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(), new Class<?>[] { Hello.class },
                handler);

        helloProxy.sayHello("alice");
    }
}
