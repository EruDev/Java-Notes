package com.eru.concurrency.imooc.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1. shutdown
 * 2. shutdownNow
 * 3. terminated
 * Created by eru on 2020/5/31.
 */
public class ShutDown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 100; i++) {
            service.execute(new ShutDownTask());
        }
        //System.out.println(service.isTerminated());
        //service.shutdown();
        //System.out.println(service.isShutdown());
        //service.execute(new ShutDownTask()); // throw RejectedExecutionException
        //Thread.sleep(10*1000);
        //System.out.println(service.isTerminated());
        Thread.sleep(1000);
        service.shutdownNow();
    }
}

class ShutDownTask implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(Thread.currentThread().getName() + "被中断");
        }
        System.out.println(Thread.currentThread().getName());
    }
}
