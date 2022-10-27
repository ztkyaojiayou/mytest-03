package com.sfauto.cloud.zkslice.listener;

import com.sfauto.cloud.zkslice.ZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomConnectionStateListener implements ConnectionStateListener {

    private static final Logger logger = LoggerFactory.getLogger(CustomConnectionStateListener.class);

    private ZookeeperClient client;

    public CustomConnectionStateListener(ZookeeperClient client) {
        this.client = client;
    }

    //@Override
    public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
        if (ConnectionState.RECONNECTED.equals(connectionState)) {
            logger.info("CustomConnectionStateListener reconnect");
            client.nodeInfoInit();
            client.sync();
        }
        if (ConnectionState.SUSPENDED.equals(connectionState)) {
            client.removeNodeInfoListener();
        }
    }

}
