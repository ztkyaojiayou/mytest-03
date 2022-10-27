package com.sfauto.cloud.zkslice.config;

import com.sfauto.cloud.zkslice.ZookeeperClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "zk")
public class ZookeeperConfig {
    private String connectString;
    private int sessionTimeoutMs;  // 会话超时时间
    private int connectionTimeoutMs; // 连接超时时间
    private String namespace; // 包含隔离名称

    @Bean
    ZookeeperClient zookeeperClient() {
        ZookeeperClient zookeeperClient = new ZookeeperClient(connectString, sessionTimeoutMs, connectionTimeoutMs, namespace);
        zookeeperClient.init();
        return zookeeperClient;
    }

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public int getSessionTimeoutMs() {
        return sessionTimeoutMs;
    }

    public void setSessionTimeoutMs(int sessionTimeoutMs) {
        this.sessionTimeoutMs = sessionTimeoutMs;
    }

    public int getConnectionTimeoutMs() {
        return connectionTimeoutMs;
    }

    public void setConnectionTimeoutMs(int connectionTimeoutMs) {
        this.connectionTimeoutMs = connectionTimeoutMs;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
