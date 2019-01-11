package com.netty.NettyRpc.proxy;

import com.netty.NettyRpc.client.ConnectManager;
import com.netty.NettyRpc.client.RpcClientHandler;
import com.netty.NettyRpc.client.RpcFuture;

import com.netty.NettyRpc.protocol.RpcRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

public class ObjectProxy<T> implements InvocationHandler, IAsyncObjectProxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectProxy.class);
    private Class<T> clazz;

    public ObjectProxy(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public RpcFuture call(String funcName, Object... args) {
        RpcClientHandler handler = ConnectManager.getInstance().chooseHandler();
        RpcRequest request = createRequest(this.clazz.getName(), funcName, args);
        RpcFuture rpcFuture = handler.sendRequest(request);
        return rpcFuture;
    }

    private RpcRequest createRequest(String name, String funcName, Object[] args) {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setRequestId(UUID.randomUUID().toString());
        rpcRequest.setClassName(name);
        rpcRequest.setMethodName(funcName);
        rpcRequest.setParameters(args);
        Class[] parameterTypes = new Class[args.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            parameterTypes[i] = getClassType(args[i]);
        }

        rpcRequest.setParameterTypes(parameterTypes);
        LOGGER.debug(name);
        LOGGER.debug(funcName);
        for (int i = 0; i < parameterTypes.length; ++i) {
            LOGGER.debug(parameterTypes[i].getName());
        }

        for (int i = 0; i < args.length; ++i) {
            LOGGER.debug(args[i].toString());
        }
        
        return rpcRequest;
    }

    private Class getClassType(Object arg) {
        Class<?> classType = arg.getClass();
        String name = classType.getName();
        switch (name) {
            case "java.lang.Integer":
                return Integer.TYPE;
            case "java.lang.Long":
                return Long.TYPE;
            case "java.lang.Float":
                return Float.TYPE;
            case "java.lang.Double":
                return Double.TYPE;
            case "java.lang.Character":
                return Character.TYPE;
            case "java.lang.Boolean":
                return Boolean.TYPE;
            case "java.lang.Short":
                return Short.TYPE;
            case "java.lang.Byte":
                return Byte.TYPE;
            default:
                return classType;
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class == method.getDeclaringClass()) {
            String name = method.getName();
            if ("equals".equals(name)) {
                return proxy == args[0];
            } else if ("hashCode".equals(name)) {
                return System.identityHashCode(proxy);
            } else if ("toString".equals(name)) {
                return proxy.getClass() + "@" + Integer.toHexString(System.identityHashCode(proxy))
                        + ", with invocationhandler " + this;
            } else {
                throw new IllegalStateException(String.valueOf(method));
            }
        }


        RpcRequest request = new RpcRequest();
        request.setRequestId(UUID.randomUUID().toString());
        request.setClassName(method.getDeclaringClass().getName());
        request.setParameterTypes(method.getParameterTypes());
        request.setParameters(args);

        // Debug
        LOGGER.debug(method.getDeclaringClass().getName());
        LOGGER.debug(method.getName());
        for (int i = 0; i < method.getParameterTypes().length; ++i) {
            LOGGER.debug(method.getParameterTypes()[i].getName());
        }

        for (int i = 0; i < args.length; ++i) {
            LOGGER.debug(args[i].toString());
        }

        RpcClientHandler handler = ConnectManager.getInstance().chooseHandler();
        RpcFuture future = handler.sendRequest(request);

        return future.get();
    }
}
