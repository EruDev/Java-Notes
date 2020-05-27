package com.eru.concurrency.base.stopthreads;

import lombok.extern.slf4j.Slf4j;

/**
 * 在while循环外捕获异常, interrupt线程, 线程并不能响应。因为异常被捕获了
 * Created by eru on 2020/5/27.
 */
@Slf4j
public class CantInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            while (num <= 300 && !Thread.currentThread().isInterrupted()){
                if (num % 100 == 0){
                    log.info(num + "是100的倍数");
                }
                num++;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
