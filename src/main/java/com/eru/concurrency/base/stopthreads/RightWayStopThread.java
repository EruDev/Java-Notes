package com.eru.concurrency.base.stopthreads;

import lombok.extern.slf4j.Slf4j;

/**
 * 传递中断
 * Created by eru on 2020/5/27.
 */
@Slf4j
public class RightWayStopThread implements Runnable{

    private static void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            log.info("go");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.info("保存日志");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThread());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
