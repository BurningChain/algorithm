package com.netty.rpc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RpcBootStrap {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring.xml");
    }
}
