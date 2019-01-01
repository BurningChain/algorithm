package com.netty.rpc.service;


import com.netty.rpc.annotation.RpcService;

@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello" + name;
    }
}
