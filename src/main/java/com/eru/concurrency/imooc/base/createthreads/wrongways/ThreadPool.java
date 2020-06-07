package com.eru.concurrency.imooc.base.createthreads.wrongways;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池创建线程
 * Created by eru on 2020/5/26.
 */
public class ThreadPool {
    private static int count;

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            service.submit(new Task());
            count++;
        }
        if (service.isShutdown()){
            service.shutdown();
            System.out.println("[count]=" + count);
        }
    }
}

class Task implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}