package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CglibProxy {

    public static void main(String[] args){


    }
}


class CglibInvocationHandler implements InvocationHandler {

    private Object object;

    public CglibInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return method.invoke(args);
    }
}


interface CglibService {

    String cglibProxy();
}

class CglibServiceImpl implements CglibService {

    @Override
    public String cglibProxy() {
        return "cglib";
    }
}