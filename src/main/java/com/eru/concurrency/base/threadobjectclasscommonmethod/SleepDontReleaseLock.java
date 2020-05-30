package com.eru.concurrency.base.threadobjectclasscommonmethod;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示sleep不会释放lock
 * Created by eru on 2020/5/30.
 */
@Slf4j
public class SleepDontReleaseLock implements Runnable{
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(new SleepDontReleaseMonitorLock()).start();
        new Thread(new SleepDontReleaseMonitorLock()).start();
    }

    @Override
    public void run() {
        lock.lock();
        log.info(Thread.currentThread().getName() +  "拿到了lock");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        log.info(Thread.currentThread().getName() +  "释放了lock");
    }
}
