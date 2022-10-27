package com.sfauto.cloud.zkslice.listener;

import com.sfauto.cloud.zkslice.ZookeeperClient;
import com.sfauto.cloud.zkslice.constant.Constant;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLeaderLatchListener implements LeaderLatchListener {

    private static final Logger logger = LoggerFactory.getLogger(CustomLeaderLatchListener.class);

    private ZookeeperClient client;

    public CustomLeaderLatchListener(ZookeeperClient client) {
        this.client = client;
    }

    //@Override
    public void isLeader() {
        logger.info("Currently run as leader");
        //成为leader之后要管理任务分片
        try {
            client.updateNodeInfo();
        } catch (Exception e) {
            logger.error("slice Exception", e);
        }
        try {
            PathChildrenCache cache = new PathChildrenCache(client.getClient(), Constant.SLICE_NODE, true);
            cache.start();

            cache.getListenable().addListener(new NodeInfoPathChildrenListener(client));
        } catch (Exception e) {
            logger.error("PathChildrenCache 异常", e);
        }
    }

    //@Override
    public void notLeader() {
        logger.info("Currently run as slave");
    }
}
