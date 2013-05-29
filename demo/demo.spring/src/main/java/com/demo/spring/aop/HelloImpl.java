package com.demo.spring.aop;

import org.springframework.stereotype.Service;

@Service
public class HelloImpl implements Hello {

    @Override
    @MethodLog("执行 问候")
    public void sayHello(String name) {
        System.out.println("hello, " + name);
    }

    public static void main(String[] args) {

        Hello hello = new HelloImpl();
        hello.sayHello("alice");
    }
}
