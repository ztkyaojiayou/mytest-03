package com.sfauto.service.jedis.service.impl;

import com.sfauto.service.jedis.service.JedisClusterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class JedisClusterServiceImpl implements JedisClusterService {

    @Autowired
    private JedisCluster pipelineCluster;

    @Override
    public long decr(String key) {
        return pipelineCluster.decr(key);
    }

    @Override
    public long incr(String key) {
        return pipelineCluster.incr(key);
    }

    @Override
    public String get(String key) {
        return pipelineCluster.get(key);
    }

    @Override
    public void set(String key, String value) {
        pipelineCluster.set(key, value);
    }

    @Override
    public void set(String key, String value, int ex) {
        set(key, value);
        expire(key, ex);
    }

    @Override
    public String getset(String key, String value) {
        return pipelineCluster.getSet(key, value);
    }

    @Override
    public String getset(String key, String value, int ex) {
        String result = getset(key, value);
        expire(key, ex);
        return result;
    }

    @Override
    public long del(String... keys) {
        return pipelineCluster.del(keys);
    }

    @Override
    public boolean exists(String key) {
        return pipelineCluster.exists(key);
    }

    @Override
    public long expire(String key, int seconds) {
        return pipelineCluster.expire(key, seconds);
    }

    @Override
    public String mset(Map<String, String> keyValueMap, int expire) {
//        if (keyValueMap == null || keyValueMap.isEmpty()) {
            return null;
//        }
//        return pipelineCluster.mset(keyValueMap, expire);
    }

    @Override
    public Map<String, String> mget(List<String> keys) {
//        if (keys == null || keys.isEmpty()) {
            return null;
//        }
//        return pipelineCluster.mget(keys);
    }

    @Override
    public long hdel(String key, String... field) {
        return pipelineCluster.hdel(key, field);
    }

    @Override
    public boolean hexists(String key, String field) {
        return pipelineCluster.hexists(key, field);
    }

    @Override
    public String hget(String key, String field) {
        return pipelineCluster.hget(key, field);
    }

    @Override
    public Map<String, String> hgetall(String key) {
        return pipelineCluster.hgetAll(key);
    }

    @Override
    public Set<String> hkeys(String key) {
        return pipelineCluster.hkeys(key);
    }

    @Override
    public long hlen(String key) {
        return pipelineCluster.hlen(key);
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        return pipelineCluster.hmget(key, fields);
    }

    @Override
    public boolean hmset(String key, Map<String, String> hashmap) {
        String result =  pipelineCluster.hmset(key, hashmap);
        if(result.equalsIgnoreCase("ok")) {
            return true;
        }else{
            log.warn("failed in hmset, error:{} ",result);
            return false;
        }
    }

    @Override
    public long hset(String key, String field, String value) {
        return pipelineCluster.hset(key, field, value);
    }

    @Override
    public long hsetnx(String key, String field, String value) {
        return pipelineCluster.hsetnx(key, field, value);
    }

    @Override
    public long hsetex(String key, String field, String value, int liveTime) {
        hset(key, field, value);
        return expire(key, liveTime);
    }

    @Override
    public long sadd(String key, String... members) {
        return pipelineCluster.sadd(key, members);
    }

    @Override
    public long scard(String key) {
        return pipelineCluster.scard(key);
    }

    @Override
    public Set<String> sdiff(String... keys) {
        return pipelineCluster.sdiff(keys);
    }

    @Override
    public Set<String> sinter(String... keys) {
        return pipelineCluster.sinter(keys);
    }

    @Override
    public Set<String> sunion(String... keys) {
        return pipelineCluster.sunion(keys);
    }

    @Override
    public boolean sismember(String key, String member) {
        return pipelineCluster.sismember(key, member);
    }

    @Override
    public Set<String> smembers(String key) {
        return pipelineCluster.smembers(key);
    }

    @Override
    public long smove(String srckey, String dstkey, String member) {
        return pipelineCluster.smove(srckey, dstkey, member);
    }

    @Override
    public String spop(String key) {
        return pipelineCluster.spop(key);
    }

    @Override
    public Set<String> spop(String key, int count) {
        return pipelineCluster.spop(key, count);
    }

    @Override
    public long srem(String key, String... members) {
        return pipelineCluster.srem(key, members);
    }

    @Override
    public long lpush(String key, String... value) {
        return pipelineCluster.lpush(key, value);
    }

    @Override
    public String lpop(String key) {
        return pipelineCluster.lpop(key);
    }

    @Override
    public long rpush(String key, String... value) {
        return pipelineCluster.rpush(key, value);
    }

    @Override
    public String rpop(String key) {
        return pipelineCluster.rpop(key);
    }

    @Override
    public ScanResult<byte[]> sscan(byte[] encode, byte[] encode1, ScanParams params) {
        return pipelineCluster.sscan(encode, encode1, params);
    }
}
