package com.netty.NettyRpc.client;

import com.netty.NettyRpc.proxy.IAsyncObjectProxy;
import com.netty.NettyRpc.proxy.ObjectProxy;
import com.netty.NettyRpc.registry.ServiceDiscovery;

import java.lang.reflect.Proxy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RpcClient {


    private String serverAddress;
    private ServiceDiscovery serviceDiscovery;
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(16, 16, 600L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(65536));

    public RpcClient(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public RpcClient(ServiceDiscovery serviceDdiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }


    public static <T> T create(Class<T> interfaceCLass) {
        return (T) Proxy.newProxyInstance(interfaceCLass.getClassLoader(), new Class<?>[]{interfaceCLass}, new ObjectProxy<T>(interfaceCLass));
    }


    public static <T> IAsyncObjectProxy createAsync(Class<T> interfaceClass) {
        return new ObjectProxy<T>(interfaceClass);
    }

    public static void submit(Runnable task) {
        threadPoolExecutor.submit(task);
    }

    public void stop() {
        threadPoolExecutor.shutdown();
        serviceDiscovery.stop();
        ConnectManager.getInstance().stop();
    }
}

