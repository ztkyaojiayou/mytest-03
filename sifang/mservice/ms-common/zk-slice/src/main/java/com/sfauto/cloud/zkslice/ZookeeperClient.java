package com.sfauto.cloud.zkslice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sfauto.cloud.zkslice.constant.Constant;
import com.sfauto.cloud.zkslice.listener.CustomConnectionStateListener;
import com.sfauto.cloud.zkslice.listener.CustomLeaderLatchListener;
import com.sfauto.cloud.zkslice.listener.NodeInfoListener;
import com.sfauto.cloud.zkslice.model.NodeInfo;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

public class ZookeeperClient {

    private static final Logger logger = LoggerFactory.getLogger(ZookeeperClient.class);

    private Gson gson = new GsonBuilder().create();

    private CuratorFramework client;
    private NodeCache nodeCache;
    private NodeInfoListener nodeInfoListener;
    public NodeInfo nodeInfo;
    private String connectString;
    private int sessionTimeoutMs;  // 会话超时时间
    private int connectionTimeoutMs; // 连接超时时间
    private String namespace; // 包含隔离名称

    public ZookeeperClient(String connectString, int sessionTimeoutMs, int connectionTimeoutMs, String namespace) {
        this.connectString = connectString;
        this.sessionTimeoutMs = sessionTimeoutMs;
        this.connectionTimeoutMs = connectionTimeoutMs;
        this.namespace = namespace;
    }

    public void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(sessionTimeoutMs)  // 会话超时时间
                .connectionTimeoutMs(connectionTimeoutMs) // 连接超时时间
                .retryPolicy(retryPolicy)
                .namespace(namespace) // 包含隔离名称
                .build();
        client.start();
        this.addConnectionStateListener();
        this.addLeaderLatch();
        this.nodeInfoInit();
    }


    /**
     * 创建持久节点
     *
     * @param path
     * @param content
     * @return
     * @throws Exception
     */
    public String createPersistentNode(String path, String content) throws Exception {
        return client.create().creatingParentContainersIfNeeded() // 递归创建所需父节点
                .withMode(CreateMode.PERSISTENT) // 创建类型为持久节点
                .forPath(path, content.getBytes()); // 目录及内容

    }

    public void createNode(CreateMode createMode, String path, String content) throws Exception {
        Stat stat = client.checkExists().forPath(path);
        if (null == stat) {
            client.create().creatingParentContainersIfNeeded() // 递归创建所需父节点
                    .withMode(createMode) // 创建类型为持久节点
                    .forPath(path, content.getBytes()); // 目录及内容
        } else {
            client.setData()
                    //.withVersion(10086) // 指定版本修改
                    .forPath(path, content.getBytes());
        }
    }

    /**
     * 创建临时节点
     *
     * @param path
     * @param content
     * @return
     * @throws Exception
     */
    public String createEphemeralSequentialNode(String path, String content) throws Exception {
        return client.create().creatingParentContainersIfNeeded() // 递归创建所需父节点
                .withMode(CreateMode.EPHEMERAL_SEQUENTIAL) // 创建类型为持久节点
                .forPath(path, content.getBytes()); // 目录及内容

    }

    public void deleteNode(String path) throws Exception {
        client.delete().deletingChildrenIfNeeded().forPath(path);
    }

    /**
     * 修改节点
     *
     * @param path
     * @param content
     * @return
     * @throws Exception
     */
    public Stat updateNode(String path, String content) throws Exception {
        return client.setData()
                //.withVersion(10086) // 指定版本修改
                .forPath(path, content.getBytes());

    }

    /**
     * 查询节点数据
     *
     * @param path
     * @return
     */
    public String getNodeData(String path) throws Exception {
        byte[] bytes = client.getData().forPath(path);
        return new String(bytes);
    }

    /**
     * 查询字节点
     *
     * @param path
     * @return
     */
    public List<String> getChildren(String path) throws Exception {
        List<String> workers = client.getChildren().forPath(path);
        return workers;

    }

    public Stat getNodeStat(String path) throws Exception {
        Stat stat = new Stat();
        client.getData()
                .storingStatIn(stat)
                .forPath(path);
        return stat;

    }

    public boolean isNodeExist(String path) throws Exception {
        return client.checkExists().forPath(path) != null;
    }

    /**
     * nodeInfo 初始化
     *
     * @throws Exception
     */
    public void nodeInfoInit() {
        try {
            if (null == nodeInfo) {
                String work = this.createEphemeralSequentialNode(Constant.SLICE_NODE_PATH, "");
                nodeInfoListener = new NodeInfoListener(this);
                this.nodeCache = this.addNodeListener(nodeInfoListener, work);
            } else {
                String work = this.createEphemeralSequentialNode(Constant.SLICE_NODE_PATH, "");
                nodeInfoListener = new NodeInfoListener(this);
                this.nodeCache = this.addNodeListener(nodeInfoListener, work);
            }
        } catch (Exception e) {
            logger.error("nodeInfoInit Exception:", e);
        }
    }

    public void addConnectionStateListener() {
        CustomConnectionStateListener customConnectionStateListener = new CustomConnectionStateListener(this);
        this.client.getConnectionStateListenable().addListener(customConnectionStateListener);
    }


    public void removeNodeInfoListener() {
        this.nodeCache.getListenable().removeListener(nodeInfoListener);
    }

    public void addLeaderLatch() {
        CustomLeaderLatchListener customLeaderLatchListener = new CustomLeaderLatchListener(this);
        try {
            this.addLeaderLatchListener(customLeaderLatchListener, Constant.LEADER_PATH);
        } catch (Exception e) {
            logger.error("addLeaderLatch Exception:", e);
        }
    }

    /**
     * 添加节点监控
     *
     * @param listener
     * @param path
     * @throws Exception
     */
    public NodeCache addNodeListener(NodeInfoListener listener, String path) throws Exception {
        NodeCache nodeCache = new NodeCache(client, path, false);
        nodeCache.start(true);
        listener.setNodeCache(nodeCache);
        nodeCache.getListenable().addListener(listener);
        return nodeCache;
    }

    public void addLeaderLatchListener(CustomLeaderLatchListener listener, String path) throws Exception {
        logger.info("addLeaderLatchListener:" + path);
        final String id = UUID.randomUUID().toString();
        logger.info("id:" + id);
        LeaderLatch leaderLatch = new LeaderLatch(client, path, id);
        leaderLatch.addListener(listener);
        leaderLatch.start();

    }

    public void updateNodeInfo() throws Exception {
        List<String> workers = client.getChildren().forPath(Constant.SLICE_NODE);
        logger.info("workers:" + workers);
        for (int i = 0; i < workers.size(); i++) {
            String path = Constant.SLICE_NODE_PARENT + workers.get(i);
            NodeInfo nodeInfo = new NodeInfo();
            nodeInfo.setNodeName(workers.get(i));
            nodeInfo.setPath(path);
            nodeInfo.setNodeId(i);
            nodeInfo.setNodeSize(workers.size());
            updateNode(path, gson.toJson(nodeInfo));
        }
    }


    public CuratorFramework getClient() {
        return client;
    }

    public void setClient(CuratorFramework client) {
        this.client = client;
    }

    public NodeInfo getNodeInfo() {
        return nodeInfo;
    }

    public void setNodeInfo(NodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
    }

    public void sync() {
        client.sync();
    }

}
