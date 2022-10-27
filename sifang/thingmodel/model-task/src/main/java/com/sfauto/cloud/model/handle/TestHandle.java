package com.sfauto.cloud.model.handle;

import com.sfauto.cloud.zkslice.ZookeeperClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestHandle {
    private Logger logger = LoggerFactory.getLogger(TestHandle.class);

    @Autowired
    ZookeeperClient zookeeperClient;

    public boolean test3() {
        logger.info(zookeeperClient.nodeInfo.getNodeId()+","+zookeeperClient.nodeInfo.getNodeSize());

        return true;
    }
}
