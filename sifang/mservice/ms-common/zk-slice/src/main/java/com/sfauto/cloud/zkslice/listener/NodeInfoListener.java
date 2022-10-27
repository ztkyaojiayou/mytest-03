package com.sfauto.cloud.zkslice.listener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sfauto.cloud.zkslice.ZookeeperClient;
import com.sfauto.cloud.zkslice.model.NodeInfo;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NodeInfoListener implements NodeCacheListener {

    private static final Logger logger = LoggerFactory.getLogger(NodeInfoListener.class);

    private ZookeeperClient client;

    public NodeInfoListener(ZookeeperClient client) {
        this.client = client;
    }

    private Gson gson = new GsonBuilder().create();

    NodeCache nodeCache;

    public void setNodeCache(NodeCache nodeCache) {
        this.nodeCache = nodeCache;
    }

    //@Override
    public void nodeChanged() {
        try {
            ChildData childData = nodeCache.getCurrentData();
            if (null != childData && null != childData.getData()) {
                String nodeInfoStr = new String(childData.getData());
                logger.info("NodeInfoListener:" + nodeInfoStr);
                if (null != nodeInfoStr && !"".equals(nodeInfoStr)) {
                    NodeInfo nodeInfo = gson.fromJson(nodeInfoStr, NodeInfo.class);
                    client.setNodeInfo(nodeInfo);
                }
            }

        } catch (Exception e) {
            logger.error("nodeChanged Exception:", e);
        }

    }
}
