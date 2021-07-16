package com.fuhao.service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @Author fuhao
 * @Date 2021/7/16 10:15 上午
 * @Version 1.0
 */
public class RedissonDistributedLocker implements DistributedLocker{

    private RedissonClient redissonClient;


    @Override
    public void lock(String key) {
        RLock lock = redissonClient.getLock(key);
        lock.lock();
    }

    @Override
    public void unlock(String key) {
        RLock lock = redissonClient.getLock(key);
        lock.unlock();
    }

    @Override
    public void lock(String key, int leaseTime) {
        RLock lock = redissonClient.getLock(key);
        lock.lock(leaseTime,TimeUnit.SECONDS);
    }

    @Override
    public void lock(String key, TimeUnit unit, int timeout) {
        RLock lock = redissonClient.getLock(key);
        lock.lock(timeout,unit);
    }

    public void setRedissonClient(RedissonClient redissonClient){
        this.redissonClient = redissonClient;
    }
}
