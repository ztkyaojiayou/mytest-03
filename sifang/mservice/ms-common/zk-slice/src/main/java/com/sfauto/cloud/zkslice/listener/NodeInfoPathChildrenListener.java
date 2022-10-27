package com.sfauto.cloud.zkslice.listener;

import com.sfauto.cloud.zkslice.ZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NodeInfoPathChildrenListener implements PathChildrenCacheListener {

    private static final Logger logger = LoggerFactory.getLogger(CustomLeaderLatchListener.class);

    private ZookeeperClient client;

    public NodeInfoPathChildrenListener(ZookeeperClient client) {
        this.client = client;
    }

    //@Override
    public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
        logger.info("Event TYpe ：" + pathChildrenCacheEvent.getType());
        if (pathChildrenCacheEvent.getType() == PathChildrenCacheEvent.Type.CHILD_ADDED) {
            //有新加入节点或者删除节点，重新分配任务
            Thread.sleep(1000);
            client.updateNodeInfo();
        }
        if (pathChildrenCacheEvent.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED) {
            //有新加入节点或者删除节点，重新分配任务
            client.updateNodeInfo();
        }
    }
}
