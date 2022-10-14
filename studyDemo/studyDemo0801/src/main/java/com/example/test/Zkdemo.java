package com.example.test;

/**
 * @author :zoutongkun
 * @date :2022/9/16 12:01 下午
 * @description :
 * @modyified By:
 */

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class Zkdemo {
    @Autowired
    CuratorFramework client;

    /**
     * 首先需要调用create 创建路径
     * 尝试获取锁 包含解锁操作
     * @param lockCallback
     * @param lockKey
     * @param timeout
     * @param <T>
     * @return
     */
    public <T> TwoTuple<Boolean, T> tryLock(LockCallback<T> lockCallback, String lockKey, Long timeout) {
        InterProcessMutex lock = new InterProcessMutex(client, lockKey);
        try {
            if (lock.acquire(timeout, TimeUnit.MILLISECONDS)) {
                log.info(Thread.currentThread().getName() + " get lock");
                return new TwoTuple<>(true, lockCallback.exec());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                log.info(Thread.currentThread().getName() + " release lock");
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new TwoTuple<>(false, null);
    }
}



