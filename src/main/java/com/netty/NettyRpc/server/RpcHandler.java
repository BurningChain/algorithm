package com.netty.NettyRpc.server;

import com.netty.rpc.request.RpcRequest;
import com.netty.rpc.response.RpcResponse;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.reflect.FastClass;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 2author miracle
 * created at 2019-01-01
 */
public class RpcHandler extends SimpleChannelInboundHandler<RpcRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcHandler.class);

    private final Map<String, Object> handlerMap;

    public RpcHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }


    @Override
    protected void channelRead0(final ChannelHandlerContext channelHandlerContext, final RpcRequest request) throws Exception {

        NettyServer.submit(new Runnable() {
            @Override
            public void run() {
                LOGGER.debug("receiver request " + request.getRequestId());
                RpcResponse response = new RpcResponse();
                response.setRequestId(request.getRequestId());

                try {
                    Object result = handler(request);
                    response.setResult(result);
                } catch (Throwable t) {
                    response.setError(t);
                    LOGGER.error("rpc server handler request error", t);
                }

                channelHandlerContext.writeAndFlush(response).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        LOGGER.debug("send response for request :" + request.getRequestId());
                    }
                });
            }
        });

    }


    private Object handler(RpcRequest request) throws InvocationTargetException {

        String className = request.getClassName();
        Object serviceBean = handlerMap.get(className);

        Class<?> serviceClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();

        LOGGER.debug(serviceClass.getName());
        LOGGER.debug(methodName);

        for (int i = 0; i < parameterTypes.length; i++) {
            LOGGER.debug(parameterTypes[i].getName());
        }

        for (Object parameter : parameters) {
            LOGGER.debug(parameter.toString());
        }

        // cglib reflect
        FastClass serviceFastClass = FastClass.create(serviceClass);
        int methodIndex = serviceFastClass.getIndex(methodName, parameterTypes);
        return serviceFastClass.invoke(methodIndex, serviceBean, parameters);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("server caught exception", cause);
        ctx.close();
    }
}
