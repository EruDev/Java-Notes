package com.eru.concurrency.base.threadobjectclasscommonmethod;

import lombok.extern.slf4j.Slf4j;

/**
 * 演示wait只释放当前的monitor锁
 * Created by eru on 2020/5/28.
 */
@Slf4j
public class WaitNotifyReleaseOwnMonitor {

    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
                    log.info("ThreadA got resourceA");
                    synchronized (resourceB){
                        log.info("ThreadA got resourceB");
                        try {
                            log.info("ThreadA release resourceA");
                            resourceA.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
        Thread.sleep(1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
                    log.info("ThreadB got resourceA");
                    log.info("ThreadB tries to got resourceB");
                    synchronized (resourceB){
                        log.info("ThreadB got resourceB");
                    }
                }
            }
        }).start();
    }
}
