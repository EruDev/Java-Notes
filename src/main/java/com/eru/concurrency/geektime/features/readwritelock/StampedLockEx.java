package com.eru.concurrency.geektime.features.readwritelock;

import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock
 * 写锁和悲观锁 跟 ReentrantReadWriteLock 类型, 非可重入锁, 并且不支持条件变量
 * ReadWriteLock 支持多个线程同时读, 但是多个线程同时读的时候
 * 所有的操作会被阻塞：而 StampedLock 提供的乐观读(无锁), 是允许一个线程获取写锁的, 也就是说不是所有写操作都被阻塞的
 *
 * 不能直接使用 interrupt 会导致 cpu100%
 * 若想中断可以使用
 * readLockInterruptibly() 和writeLockInterruptibly()
 * Created by eru on 2020/6/10.
 */
public class StampedLockEx {

    private final StampedLock sl = new StampedLock();

    /**
     * 获取释放 悲观读锁
     * @return
     */
    Object get(){
        long stamp = sl.readLock();
        try {
            // 省略业务代码
            return new Object();
        }finally {
            sl.unlockRead(stamp);
        }
    }

    /**
     * 获取释放 写锁
     */
    void set(){
        long stamp = sl.writeLock();
        try {
            // 省略业务代码
        }finally {
            sl.unlockWrite(stamp);
        }
    }

    /**
     * 乐观读官方示例
     */
    static class Point{
        private int x, y;
        final StampedLock sl = new StampedLock();

        Double distanceFromOrigin(){
            // 乐观读
            long stamp = sl.tryOptimisticRead();
            // 读入局部变量
            // 读的时候可能被修改
            int curX = x, curY = y;
            // 判断执行读操作期间
            // 是否存在写操作, 如果存在
            // 则sl.validate 返回 false
            if (!sl.validate(stamp)){
                // 升级悲观读锁
                stamp = sl.readLock();
                try {
                    curX = x;
                    curY = y;
                }finally {
                    sl.unlockRead(stamp);
                }
            }
            return Math.sqrt(curX * curX + curY * curY);
        }
    }
}
