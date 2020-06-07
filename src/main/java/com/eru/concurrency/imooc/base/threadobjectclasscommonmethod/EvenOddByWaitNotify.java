package com.eru.concurrency.imooc.base.threadobjectclasscommonmethod;

import lombok.extern.slf4j.Slf4j;

/**
 * 两个线程交替打印奇偶数 by wait notify
 * Created by eru on 2020/5/29.
 */
@Slf4j
public class EvenOddByWaitNotify {

    private static int count = 0;
    private static final Object lock = new Object();

    static class Turning implements Runnable{

        @Override
        public void run() {
            while (count <= 100){
                // 拿到锁就打印, 打印完通知另外的线程
                synchronized (lock){
                    log.info(Thread.currentThread().getName() + count++);
                    lock.notify();

                    // 如果任务还没结束, 就让出当前的锁, 并且自己休眠
                    if (count <= 100){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Turning(), "偶数").start();
        Thread.sleep(100);
        new Thread(new Turning(), "奇数").start();
    }
}
