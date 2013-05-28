package com.demo.java.annotation;

public class HelloImpl implements Hello {

    @Override
    public void sayHello(String name) {
        System.out.println("hello, " + name);
    }

    public static void main(String[] args) {

        Hello hello = new HelloImpl();
        hello.sayHello("alice");
    }
}
