package com.example.test;

import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
/**
 * @author :zoutongkun
 * @date :2022/10/13 9:18 下午
 * @description :
 * @modyified By:
 */
public class LocalCacheTest {
  // 测试类
  public static void main(String[] args) throws Exception {
    CacheService us = new CacheService();
    for (int i = 0; i < 6; i++) {
      System.out.println(us.getName("1001"));
      TimeUnit.SECONDS.sleep(1);
    }
  }

  // 实现类
  public static class CacheService {
    private final LoadingCache<String, String> cache;

    public CacheService() {
      /** 创建本地缓存，当本地缓存不命中时，调用load方法，返回结果，再缓存结果, 3秒自动过期 */
      cache =
          CacheBuilder.newBuilder()
              .expireAfterWrite(3, TimeUnit.SECONDS)
              .build(
                  new CacheLoader<String, String>() {
                    @Override
                    public String load(String id) throws Exception {
                      System.out.println("load()method invoke, 执行查询数据库, 等其他复杂的逻辑");
                      TimeUnit.MILLISECONDS.sleep(100);
                      return "User:" + id;
                    }
                  });
    }

    public String getName(String id) throws Exception {
      long start = System.currentTimeMillis();
      String result = cache.get(id);
      System.out.println("查询 " + id + " 耗时：" + (System.currentTimeMillis() - start) + " ms");
      return result;
    }
  }
}
