package com.netty.rpc.server;

import com.netty.rpc.annotation.RpcService;
import com.netty.rpc.encode.RpcDecoder;
import com.netty.rpc.encode.RpcEncoder;
import com.netty.rpc.handler.RpcHandler;
import com.netty.rpc.registry.ServiceRegistry;
import com.netty.rpc.request.RpcRequest;
import com.netty.rpc.response.RpcResponse;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.commons.collections4.MapUtils;
import org.apache.zookeeper.server.quorum.ServerBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

public class RpcServer implements ApplicationContextAware, InitializingBean {


    private static final Logger LOGGER = LoggerFactory.getLogger(RpcServer.class);
    private ServiceRegistry serviceRegistry;
    private String serverAddress;

    private Map<String, Object> handlerMap = new HashMap<>();

    public RpcServer(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public RpcServer(ServiceRegistry serviceRegistry, String serverAddress) {
        this.serviceRegistry = serviceRegistry;
        this.serverAddress = serverAddress;
    }



    @Override
    public void afterPropertiesSet() throws Exception {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new RpcDecoder(RpcRequest.class))
                                    .addLast(new RpcEncoder(RpcResponse.class))
                                    .addLast(new RpcHandler(handlerMap));
                        }
                    });

            String[] array = serverAddress.split(":");
            String host = array[0];
            Integer port = Integer.valueOf(array[1]);

            ChannelFuture channelFuture = serverBootstrap.bind(host, port).sync();
            LOGGER.debug("SERVER BIND : {}, {}", host, port);

            if (serviceRegistry != null) {
                serviceRegistry.register(serverAddress);
            }

            channelFuture.channel().closeFuture().sync();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (MapUtils.isNotEmpty(serviceBeanMap)) {
            for (Object serviceBean : serviceBeanMap.values()) {
                String interfaceName = serviceBean.getClass().getAnnotation(RpcService.class).value().getName();
                handlerMap.put(interfaceName, serviceBean);
            }
        }

    }
}
