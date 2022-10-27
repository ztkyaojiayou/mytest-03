package com.sfauto.service.jedis;

import com.sfauto.service.jedis.config.JedisClusterConfig;
import com.sfauto.service.jedis.service.JedisClusterService;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Connection;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;
import redis.clients.jedis.util.SafeEncoder;

import java.util.*;

@SpringBootTest
public class JedisApplicationTest {

//    @Qualifier("jedisCluster")
//    @Autowired
//    JedisCluster jedisCluster;

    @Test
    public void testJedisCluster() {

        GenericObjectPoolConfig<Connection> config = new GenericObjectPoolConfig<Connection>();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        config.setMinIdle(5);

        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
        jedisClusterNode.add(new HostAndPort("192.188.1.244", 6379));
        jedisClusterNode.add(new HostAndPort("192.188.1.245", 6379));
        jedisClusterNode.add(new HostAndPort("192.188.1.246", 6379));

        JedisCluster jedisCluster = null;
        try {
            //connectionTimeout：指的是连接一个url的连接等待时间
            //soTimeout：指的是连接上一个url，获取response的返回等待时间
            jedisCluster = new JedisCluster(jedisClusterNode, 6000, 5000, 10, "sf@123456", config);
            System.out.println(jedisCluster.set("cluster", "edsion"));
            System.out.println(jedisCluster.get("cluster"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedisCluster != null)
                jedisCluster.close();
        }
    }
}
