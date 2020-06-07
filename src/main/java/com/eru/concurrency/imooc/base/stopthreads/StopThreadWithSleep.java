package com.eru.concurrency.imooc.base.stopthreads;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程在运行中 调用sleep，此时在休眠中也能正常响应 interrupt
 * Created by eru on 2020/5/27.
 */
@Slf4j
public class StopThreadWithSleep{

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;

            try {
                while (num <= 300 && !Thread.currentThread().isInterrupted()){
                    if (num % 100 == 0){
                        log.info(num + "是100的倍数");
                    }
                    num++;
                }
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
