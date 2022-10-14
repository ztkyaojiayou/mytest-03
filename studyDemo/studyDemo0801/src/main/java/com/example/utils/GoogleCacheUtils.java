package com.example.utils;

import javax.annotation.PostConstruct;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;

/**
 * @author :zoutongkun
 * @date :2022/10/12 3:02 下午
 * @description :
 * @modyified By:
 */
public class GoogleCacheUtils {
    /** 缓存值的最大数 */
    private int maximumSize = 1000;

    /** 缓存过期分钟数 */
    private int expireAfterAccessMinutes = 60 * 72;

    private Cache<String, Object> localCache = null;

    public GoogleCacheUtils() {
      super();
    }

    public int getMaximumSize() {
      return maximumSize;
    }

    public void setMaximumSize(int maximumSize) {
      this.maximumSize = maximumSize;
    }

    public int getExpireAfterAccessMinutes() {
      return expireAfterAccessMinutes;
    }

    public void setExpireAfterAccessMinutes(int expireAfterAccessMinutes) {
      this.expireAfterAccessMinutes = expireAfterAccessMinutes;
    }

    // 项目启动时加载
    @PostConstruct
    public void init() {
      localCache =
          CacheBuilder.newBuilder()
              .maximumSize(this.maximumSize)
              .expireAfterAccess(this.expireAfterAccessMinutes * 60L, TimeUnit.SECONDS)
              .build();
    }

    public Object get(String key) {
      return this.localCache.getIfPresent(key);
    }

    public void put(String key, Object o) {
      this.localCache.put(key, o);
    }

    public void invalidate(String key) {
      this.localCache.invalidate(key);
    }
}
