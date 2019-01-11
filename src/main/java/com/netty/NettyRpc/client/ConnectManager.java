package com.netty.NettyRpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectManager.class);
    private volatile static ConnectManager connectManager;

    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup(4);
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(16, 16, 600L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(65536));

    private CopyOnWriteArrayList<RpcClientHandler> connectedHandlers = new CopyOnWriteArrayList<>();
    private Map<InetSocketAddress, RpcClientHandler> connectedServerNodes = new ConcurrentHashMap<>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition connected = lock.newCondition();
    private long connectTimeoutMillis = 6000;
    private AtomicInteger roundRobin = new AtomicInteger(0);
    private volatile boolean isRunning = true;

    private ConnectManager() {

    }

    public static ConnectManager getInstance() {
        if (null == connectManager) {
            synchronized (ConnectManager.class) {
                if (null == connectManager) {
                    connectManager = new ConnectManager();
                }
            }
        }
        return connectManager;
    }


    public void updateConnectedServer(List<String> allServerAddress) {
        if (null != allServerAddress) {
            if (allServerAddress.size() > 0) {
                HashSet<InetSocketAddress> newAllServerNodeSet = new HashSet<>();
                for (String serverAddress : allServerAddress) {
                    String[] strings = serverAddress.split(":");
                    if (strings.length == 2) {
                        String host = strings[0];
                        int port = Integer.valueOf(strings[1]);
                        final InetSocketAddress remotePeer = new InetSocketAddress(host, port);
                        newAllServerNodeSet.add(remotePeer);
                    }
                }

                for (final InetSocketAddress serverNodeAddress : newAllServerNodeSet) {
                    if (!connectedServerNodes.keySet().contains(serverNodeAddress)) {
                        connectServerNode(serverNodeAddress);
                    }
                }

                for (RpcClientHandler handler : connectedHandlers) {

                    RpcClientHandler connectedServerHandler = handler;
                    SocketAddress remotePeer = connectedServerHandler.getRemotePeer();
                    if (!newAllServerNodeSet.contains(remotePeer)) {
                        LOGGER.info("remove invalid server node " + remotePeer);
                        RpcClientHandler rpcClientHandler = connectedServerNodes.get(remotePeer);
                        if (handler != null) {
                            handler.close();
                        }
                        connectedServerNodes.remove(remotePeer);
                        connectedHandlers.remove(connectedServerHandler);
                    }
                }

            } else {
                LOGGER.error("no available server node. all server nodes are down !!!");
                for (final RpcClientHandler connectedServerHandler : connectedHandlers) {
                    SocketAddress remotePeer = connectedServerHandler.getRemotePeer();
                    RpcClientHandler handler = connectedServerNodes.get(remotePeer);
                    handler.close();
                    connectedServerNodes.remove(connectedServerHandler);
                }
                connectedHandlers.clear();
            }

        }
    }


    public void reconnect(final RpcClientHandler handler, final SocketAddress remotePeer) {
        if (null != handler) {

            connectedHandlers.remove(handler);
            connectedServerNodes.remove(handler.getRemotePeer());
        }
        connectServerNode((InetSocketAddress) remotePeer);
    }


    private void connectServerNode(final InetSocketAddress remotePeer) {

        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {

                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new RpcChannelInitializer());

                ChannelFuture future = bootstrap.connect(remotePeer);
                future.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (channelFuture.isSuccess()) {
                            LOGGER.debug("successfully connect to remotepeer :{}", remotePeer);
                            RpcClientHandler handler = channelFuture.channel().pipeline().get(RpcClientHandler.class);

                            addHandler(handler);
                        }
                    }
                });
            }
        });
    }


    private void addHandler(RpcClientHandler handler) {
        connectedHandlers.add(handler);
        InetSocketAddress remotePeer = (InetSocketAddress) handler.getChannel().remoteAddress();
        connectedServerNodes.put(remotePeer, handler);
        signalAvailableHandler();
    }

    private void signalAvailableHandler() {

        lock.lock();
        try {
            connected.signalAll();
        } finally {
            lock.unlock();
        }
    }


    private boolean waitingForHandler() throws  InterruptedException {
        lock.lock();
        try {
            return connected.await(this.connectTimeoutMillis, TimeUnit.MICROSECONDS);
        } finally {
            lock.unlock();
        }
    }

    public RpcClientHandler chooseHandler() {

        int size = connectedHandlers.size();
        try {
            while (isRunning && size <= 0) {
                boolean b = waitingForHandler();
                if (b) {
                    size = connectedHandlers.size();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            LOGGER.error("waiting for available node is interrupted, {}", e);
            throw new RuntimeException("can not connect any servers! {}", e);
        }

        int index = (roundRobin.getAndAdd(1) + size) % size;
        return connectedHandlers.get(index);
    }


    public void stop() {

        isRunning = false;
        for (int i = 0; i < connectedHandlers.size(); i++) {
            RpcClientHandler connectedServerHandler = connectedHandlers.get(i);
            connectedServerHandler.close();
        }
        signalAvailableHandler();
        threadPoolExecutor.shutdown();
        eventLoopGroup.shutdownGracefully();
    }
}



