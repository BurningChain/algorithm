package com.netty.rpc.registry;

import com.netty.rpc.util.Constant;
import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ServiceRegistry {

    public static final Logger LOGGER = LoggerFactory.getLogger(ServiceRegistry.class);
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private String registryAddress;

    public ServiceRegistry(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    public void register(String data) {
        if (data != null) {
            ZooKeeper connector = connector();
            if (connector != null) {
                createNode(connector, data);
            }
        }
    }


    public ZooKeeper connector() {

        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(registryAddress, Constant.ZK_SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                        countDownLatch.countDown();
                    }
                }
            });
            countDownLatch.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return zk;
    }

    private void createNode(ZooKeeper zk, String data) {
        byte[] bytes = data.getBytes();
        String path = null;
        try {
            path = zk.create(Constant.ZK_DATA_PATH, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            LOGGER.debug("create zookeeper node ({} ==> {})", path, data);
        } catch (KeeperException e) {
            LOGGER.error(" ======================= ");
        } catch (InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(" ======================= ");
        }

    }

}
