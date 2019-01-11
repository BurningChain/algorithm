package com.netty.NettyRpc.proxy;

import com.netty.NettyRpc.client.RpcFuture;

public interface IAsyncObjectProxy {

    RpcFuture call(String funcName, Object... args);
}
