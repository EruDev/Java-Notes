package com.eru.concurrency.base.stopthreads;

import lombok.extern.slf4j.Slf4j;

/**
 * 在循环中调用sleep, 此时Thread.currentThread().isInterrupted() 是不起作用的
 * Created by eru on 2020/5/27.
 */
@Slf4j
public class StopThreadEveryLoopWithSleep {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;

            try {
                while (num <= 10000){
                    if (num % 100 == 0){
                        log.info(num + "是100的倍数");
                    }
                    Thread.sleep(10);
                    num++;
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
