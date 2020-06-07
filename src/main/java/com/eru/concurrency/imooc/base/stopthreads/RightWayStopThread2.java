package com.eru.concurrency.imooc.base.stopthreads;

import lombok.extern.slf4j.Slf4j;

/**
 * 恢复中断
 * Created by eru on 2020/5/27.
 */
@Slf4j
public class RightWayStopThread2 implements Runnable{

    private static void reInterrupt(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            if (Thread.currentThread().isInterrupted()){
                log.info("Interrupted  程序中断 结束..");
                break;
            }
            reInterrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThread2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
