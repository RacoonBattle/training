package com.demo.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyInvocationHandler implements InvocationHandler {

    private Object target;

    public ProxyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        boolean methodLogAnnotated = method.isAnnotationPresent(MethodLog.class);

        if (methodLogAnnotated) {
            System.out.println(method.getName() + " Begin.");
        }

        Object ret = method.invoke(target, args);

        if (methodLogAnnotated) {
            System.out.println(method.getName() + " End.");
        }
        return ret;
    }
}
