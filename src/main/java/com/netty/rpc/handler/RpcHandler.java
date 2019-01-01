package com.netty.rpc.handler;

import com.netty.rpc.request.RpcRequest;
import com.netty.rpc.response.RpcResponse;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import java.util.Map;

public class RpcHandler extends SimpleChannelInboundHandler<RpcRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcHandler.class);
    private final Map<String, Object> handlerMap;

    public RpcHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcRequest rpcRequest) throws Exception {

        RpcResponse response = new RpcResponse();
        response.setRequestId(response.getRequestId());

        try {
            Object result = handler(rpcRequest);
            response.setResult(result);
        } catch (Throwable throwable) {
            response.setError(throwable);
            throwable.printStackTrace();
        }

        channelHandlerContext.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        LOGGER.error("caught exception :" + cause.getMessage());
        ctx.close();
    }

    private Object handler(RpcRequest request) throws Throwable {

        String className = request.getClassName();
        Object serviceBean = handlerMap.get(className);
        Class<?> serviceBeanClass = serviceBean.getClass();

        String methodName = request.getMethodName();
        Object methodBean = handlerMap.get(methodName);

        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();

        FastClass fastClass = FastClass.create(serviceBeanClass);
        FastMethod fastClassMethod = fastClass.getMethod(methodName, parameterTypes);

        return fastClassMethod.invoke(serviceBean, parameters);


    }



}
