package com.eru.concurrency.base.sixstate;

import lombok.extern.slf4j.Slf4j;

/**
 * 演示BLOCKED WAITING TIMED_WAITING
 * Created by eru on 2020/5/28.
 */
@Slf4j
public class BlockedWaitingTimedWaiting implements Runnable{
    @Override
    public void run() {
        syn();
    }

    private synchronized void syn(){
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread.start();
        thread2.start();
        log.info(thread.getName() + "-" + thread.getState());
        log.info(thread2.getName() + "-" + thread2.getState());
        try {
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(thread.getName() + "-" + thread.getState());
        log.info(thread2.getName() + "-" + thread2.getState());
    }
}
