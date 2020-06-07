package com.eru.concurrency.imooc.base.threadobjectclasscommonmethod;

import lombok.extern.slf4j.Slf4j;

/**
 * 两个线程交替打印奇偶数 by synchronized
 * Created by eru on 2020/5/29.
 */
@Slf4j
public class EvenOddBySyn {

    private static int count = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100){
                    synchronized (lock){
                        if (count % 2 == 0){
                            log.info(Thread.currentThread().getName() + ": " + count++);
                        }
                    }
                }
            }
        }, "偶数").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100){
                    synchronized (lock){
                        if (count % 2 != 0){
                            log.info(Thread.currentThread().getName() + ": " + count++);
                        }
                    }
                }
            }
        }, "奇数").start();
    }
}
