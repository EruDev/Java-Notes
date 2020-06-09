package com.eru.concurrency.geektime.features.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 缓存 by ReadWriteLock
 * Created by eru on 2020/6/10.
 */
public class CacheByReadWriteLock<K, V> {

    /** 存储数据 */
    private final Map<K, V> map = new HashMap<>();

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    /** 读锁 */
    private final Lock readLock =  lock.readLock();

    /** 写锁 */
    private final Lock writeLock =  lock.readLock();

    /**
     * 写缓存
     * @param key key
     * @param value val
     */
    public void put(K key, V value){
        writeLock.lock();
        try {
            map.put(key, value);
        }finally {
            writeLock.unlock();
        }
    }

    /**
     * 读缓存
     * @param key key
     * @return val
     */
    public V get(K key){
        readLock.lock();
        try {
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }

    /**
     * 按需加载
     * @param key k
     * @return val
     */
    public V get2(K key){
        V v = null;
        // 先读缓存
        readLock.lock();
        try {
            v = map.get(key);
        }finally {
            readLock.unlock();
        }

        // 如果缓存中存在 直接返回
        if (v != null){
            return v;
        }

        // 缓存中不存在, 查询数据库
        writeLock.lock();
        try {
            // 再次验证
            // 高并发场景下其他线程可能已经查询过数据库
            v = map.get(key);
            if (v == null){
                // 省略查询数据库代码
                //v = xxx
                map.put(key, v);
            }
        }finally {
            writeLock.unlock();
        }
        return v;
    }
}
