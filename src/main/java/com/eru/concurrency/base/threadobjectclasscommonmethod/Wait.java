package com.eru.concurrency.base.threadobjectclasscommonmethod;

import lombok.extern.slf4j.Slf4j;

/**
 * 演示wait用法
 * Created by eru on 2020/5/28.
 */
@Slf4j
public class Wait {

    private static Object object = new Object();

    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                log.info("线程：" + Thread.currentThread().getName() + "开始执行了..");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("线程：" + Thread.currentThread().getName() + "又拿到锁了、、");
            }
        }
    }
    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                object.notify();
                log.info("线程：" + Thread.currentThread().getName() + "执行了notify()");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        Thread.sleep(100);
        thread2.start();
    }
}
