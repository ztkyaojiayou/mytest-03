package com.example.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @author :zoutongkun
 * @date :2022/9/16 11:54 上午
 * @description :
 * @modyified By:
 */
@Slf4j
@Configuration
public class ZkCoreClient {
    // zk 服务端集群地址
    @Value("${zk.url}")
    private String zkUrl;

    // session 超时时间
    private int timeOut = 60000;

    // zkclient 重试间隔时间
    private int baseSleepTimeMs = 5000;

    //zkclient 重试次数
    private int retryCount = 5;


    /**
     * 使用double-check 创建client
     *
     * @return
     */
    @Bean
    public CuratorFramework init() {
        CuratorFramework client = CuratorFrameworkFactory
                .builder()
                .connectString(zkUrl)
                .sessionTimeoutMs(timeOut)
                .retryPolicy(new ExponentialBackoffRetry(baseSleepTimeMs, retryCount))
//                            .namespace(appName)
                .build();
        // 或者使用工厂模式
//                    client = CuratorFrameworkFactory.newClient(zkUrl,new ExponentialBackoffRetry(baseSleepTimeMs,retryCount)).usingNamespace(appName);
        client.start();
        log.info("client is created at ================== {}", LocalDateTime.now());

        return client;
    }

}

