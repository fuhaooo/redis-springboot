package com.fuhao.utils;

import com.fuhao.service.DistributedLocker;

import java.util.concurrent.TimeUnit;

/**
 * @Author fuhao
 * @Date 2021/7/16 10:03 上午
 * @Version 1.0
 * 封装redis分布式锁工具类
 */
public class RedisLockUtil {

    private static DistributedLocker redisLock;

    public static void setLocker(DistributedLocker locker){
        redisLock = locker;
    }

    public static void lock(String key){
        redisLock.lock(key);
    }

    public static void unlock(String key){
        redisLock.unlock(key);
    }

    /**
     * 带超时时间的锁
     * @param key
     * @param timeout   超时时间，单位秒
     */
    public static void lock(String key,int timeout){
        redisLock.lock(key,timeout);
    }

    /**
     * 带超时时间的锁
     * @param key
     * @param unit  时间单位
     * @param timeout  超时时间
     */
    public static void lock(String key, TimeUnit unit,int timeout){
        redisLock.lock(key,unit,timeout);
    }

}
