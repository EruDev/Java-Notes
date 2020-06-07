package com.eru.concurrency.imooc.base.threadobjectclasscommonmethod;

import lombok.extern.slf4j.Slf4j;

/**
 * 演示notifyAll 用法
 * Created by eru on 2020/5/28.
 */
@Slf4j
public class WaitNotifyAll implements Runnable{

    private static Object resourceA = new Object();

    @Override
    public void run() {
        synchronized (resourceA){
            log.info(Thread.currentThread().getName() + "get resourceA.");
            try {
                resourceA.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName() + "waiting to end.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyAll runnable = new WaitNotifyAll();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
                    log.info(Thread.currentThread().getName() + "notifyAll.");
                    resourceA.notifyAll();
                }
            }
        }).start();
    }
}
