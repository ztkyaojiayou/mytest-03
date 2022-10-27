package com.sfauto.service.jedis;

import com.sfauto.service.jedis.service.JedisClusterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;
import redis.clients.jedis.util.SafeEncoder;

import java.util.*;

@SpringBootTest(classes = JedisApplication.class)
public class JedisClusterServiceTest extends AbstractJUnit4SpringContextTests {

    @Qualifier("redisService")
    @Autowired
    JedisClusterService jedisClusterService;

    private final List<String> keys = new ArrayList<String>();

    @BeforeEach
    public void before() {
        for (int index = 1; index < 10; index++) {
            String key = "key:" + index;
            keys.add(key);
        }
    }

    @Test
    public void testMset() throws Exception {

//        Map<String, String> kvs = new HashMap<>();
//        kvs.put("ana:001", "20.5f");
//        kvs.put("ana:002", "22.5f");
//        kvs.put("ana:003", "30.5f");
//        kvs.put("ana:004", "14.5f");
//        pipelineCluster.mset(kvs);

        jedisClusterService.set("ana:002", "12.5");
        System.out.println(jedisClusterService.get("ana:002"));
    }

    @Test
    public void testMHgetAll() {

        List<String> keys = new ArrayList<String>();
        keys.add("ugc:video:feature:5946211");
        keys.add("ugc:video:feature:30491583");
        keys.add("ugc:video:feature:63108807");
        keys.add("ugc:video:feature:77257903");
        keys.add("ugc:video:feature:10113377");
        keys.add("ugc:video:feature:30542906");
        keys.add("ugc:video:feature:54608980");
        keys.add("ugc:video:feature:72082818");

        Map<String, Map<String, String>> mv = new HashMap<>();
        for(int i=0;i<8;i++){
            Map<String, String> val = new HashMap<String, String>();
            val.put("k1_"+i, "v1_"+i);
            val.put("k2_"+i, "v2_"+i);
            val.put("k3_"+i, "v3_"+i);
            val.put("k4_"+i, "v4_"+i);
            val.put("k5_"+i, "v5_"+i);
            mv.put(keys.get(i), val);
        }

//        Map<String, String> map = pipelineCluster.mhmset(mv);
//        Map<String, Map<String, String>> mmap = pipelineCluster.mHgetAll(keys);
//        for (Map.Entry<String, Map<String, String>> entry : mmap.entrySet()) {
//            System.out.println(entry.getKey());
//            for (Map.Entry<String, String> entry2 : entry.getValue().entrySet()) {
//                System.out.println("\t" + entry2.getKey() + ":" + entry2.getValue());
//            }
//        }
    }

    @Test
    public void testMget() throws Exception {
//        Map<String, String> keyValues = pipelineCluster.mget(keys);
//        System.out.println(keyValues);
//
//        List<String> kk = new ArrayList<>();
//        kk.add("scada:ana:001");
//        keyValues = pipelineCluster.mget(kk);
//        System.out.println(keyValues);
    }

    @Test
    public void testMdel() throws Exception {
//        long deleteCount = pipelineCluster.mdel(keys);
//        System.out.println(deleteCount);
    }

    @Test
    public void testHget(){
        boolean iskey = jedisClusterService.hexists("soms:alarm:new", "sus1:upperclear:0");
        String hqalarms = jedisClusterService.hget("soms:alarm:new", "sus1:upperclear:0");
        System.out.println(hqalarms);
    }

    @Test
    public void testSScan() throws Exception {
        String key = "sscan:test:1";
        for (int i = 1; i <= 500; i++) {
            jedisClusterService.sadd(key, "v=" + i);
        }

        String key2 = "sscan:test:2";
        String[] aa = new String[500];
        for (int i = 0; i < 500; i++) {
            //pipelineCluster.sadd(key, "v=" + i);
            aa[i] = "v=" + i;
        }
        jedisClusterService.sadd(key2, aa);

        String cursor = "0";
        int count = 200;
        ScanParams params = new ScanParams();
        params.count(count);
        while (true) {
            ScanResult<byte[]> sscan = jedisClusterService.sscan(SafeEncoder.encode(key2), SafeEncoder.encode(cursor), params);
            List<byte[]> list = sscan.getResult();
            cursor = sscan.getCursor();
            System.out.println("cursor=" + cursor + " size" + list.size());
            if (cursor.equals("0")) {
                break;
            }
        }

        //pipelineCluster.del(key);
    }

    @Test
    public void testHMset() throws Exception{
        String key = "hmset:test:1";
        Map<String, String> hashmap = new HashMap<>();
        hashmap.put("001", "vv0001");
        hashmap.put("002", "vv0002");
        hashmap.put("003", "vv0003");
        jedisClusterService.hmset(key, hashmap);
        System.out.printf("result : %s", jedisClusterService.hmget(key, "001", "003"));
    }

    @Test
    public void testHset(){
        String key = "we:media:24:key";
        jedisClusterService.hsetex(key, "field01", "value01", 200);
        jedisClusterService.hsetex(key, "field02", "value02",200);
        jedisClusterService.hsetex(key, "field03", "value03", 200);
        Set<String> keys = jedisClusterService.hkeys(key);
        System.out.println(keys.size());
        for (String vid : keys) {
            System.out.println(vid);
        }
    }

}
