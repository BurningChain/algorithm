package com.proxy;

import sun.awt.AWTAccessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InvocationProxy {

    public static void main(String[] args){
        ProtoService protoService = new ProtoServiceImpl();
        MyInvocation invocation = new MyInvocation(protoService);
        ProtoService o = (ProtoService) Proxy.newProxyInstance(invocation.getClass().getClassLoader()
                , protoService.getClass().getInterfaces(), invocation);
        String test = o.doService("test");
        
    }
}


class MyInvocation implements InvocationHandler {

    private Object object;

    public MyInvocation() {
    }

    public MyInvocation(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy start ============");
        Object invoke = method.invoke(object, args);
        return invoke;
    }
}


interface ProtoService {

    String doService(String string);
}

class ProtoServiceImpl implements ProtoService{

    @Override
    public String doService(String string) {

        System.out.println("thie is the proto service:" + string);
        return string;
    }

}
