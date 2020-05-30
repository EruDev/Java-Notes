package com.eru.concurrency.base.threadobjectclasscommonmethod;

import lombok.extern.slf4j.Slf4j;

/**
 * 演示Sleep不会释放锁
 * Created by eru on 2020/5/30.
 */
@Slf4j
public class SleepDontReleaseMonitorLock implements Runnable{
    public static void main(String[] args) {
        new Thread(new SleepDontReleaseMonitorLock()).start();
        new Thread(new SleepDontReleaseMonitorLock()).start();
    }

    private synchronized static void syn() {
        try {
            log.info(Thread.currentThread().getName() + "拿到monitor锁");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(Thread.currentThread().getName() + "释放monitor锁");
    }

    @Override
    public void run() {
        syn();
    }
}
