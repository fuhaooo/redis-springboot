package com.fuhao.service;

import java.util.concurrent.TimeUnit;

/**
 * @Author fuhao
 * @Date 2021/7/16 10:07 上午
 * @Version 1.0
 */
public interface DistributedLocker {

    /**
     * 根据key上锁
     * @param key
     */
    void lock(String key);

    /**
     * 释放锁
     * @param key
     */
    void unlock(String key);

    /**
     * 对锁设置释放时间
     * @param key
     * @param timeout
     */
    void lock(String key,int timeout);

    /**
     * 对释放时间可自定义时间单位
     * @param key
     * @param unit
     * @param timeout
     */
    void lock(String key, TimeUnit unit,int timeout);
}
