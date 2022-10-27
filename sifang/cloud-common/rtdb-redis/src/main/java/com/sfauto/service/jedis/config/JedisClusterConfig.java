package com.sfauto.service.jedis.config;

import com.sfauto.base.global.utils.CheckStrUtils;
import com.sfauto.service.jedis.condition.ClusterCondition;
import com.sfauto.service.jedis.consts.JedisConstant;
import com.sfauto.service.jedis.service.JedisClusterService;
import com.sfauto.service.jedis.service.impl.JedisClusterServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Configuration
public class JedisClusterConfig {

    @Value("${spring.redis.hosts:192.188.1.245}")
    private String hosts;

    @Value("${spring.redis.port:6379}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout:2000}")
    private int timeout;

    @Value("${redis.cluster.nodes}")
    private Set<String> redisNodes;

    @Value("${redis.cluster.pool.max-active}")
    private int maxTotal;

    @Value("${redis.cluster.pool.max-idle}")
    private int maxIdle;

    @Value("${redis.cluster.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.pool.max-wait:3000}")
    private long maxWaitMillis;

    @Bean("jedisCluster")
    @Conditional({ClusterCondition.class})
    public JedisCluster redisPoolFactory() {

        GenericObjectPoolConfig<Connection> poolConfig = new GenericObjectPoolConfig<>();
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxWaitMillis(maxWaitMillis);

        Set<HostAndPort> nodes = new HashSet<HostAndPort>();

        if(hosts == null){
            log.error("check property of 'spring.redis.hosts', null is not allowed");
            throw new RuntimeException("'spring.redis.hosts' is null,check property");
        }

        String[] host = hosts.split(",");
        for(String str : host){
            str = str.trim();
            if(str.isEmpty()){
                log.warn("host of 'spring.redis.hosts' is empty!");
                continue;
            }

            String[] hp = str.split(":");
            String h;
            String p;
            if(hp.length == 2){
                h = hp[0];
                p = hp[1];
            }else{
                h = hp[0];
                p = "6379";
            }
            //if(CheckStrUtils.isIP(h) && CheckStrUtils.isIntNumber(p)){
            if(CheckStrUtils.isIntNumber(p)){
                nodes.add(new HostAndPort(h, Integer.valueOf(p)));
            }

        }

        return new JedisCluster(nodes, timeout, timeout, JedisConstant.DEFAULT_MAX_REDIRECTIONS, password, poolConfig);

    }

    @Bean("redisService")
    @Conditional({ClusterCondition.class})
    public JedisClusterService clusterService() {
        return new JedisClusterServiceImpl();
    }
}
