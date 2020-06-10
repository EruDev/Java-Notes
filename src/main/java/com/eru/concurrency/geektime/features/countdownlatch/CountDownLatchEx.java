package com.eru.concurrency.geektime.features.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch 主要用来解决一个线程等待多个线程的场景
 * Created by eru on 2020/6/10.
 */
public class CountDownLatchEx {

    public static void main(String[] args) throws InterruptedException {
        // 创建 2 个线程的线程池
        ExecutorService executor = Executors.newFixedThreadPool(2);
        // 计数器初始化为2
        CountDownLatch countDownLatch = new CountDownLatch(2);

        executor.submit(() -> {
            System.out.println("t1");
            countDownLatch.countDown();
        });

        executor.submit(() -> {
            System.out.println("t2");
            countDownLatch.countDown();
        });

        //executor.shutdown();
        // 等待两个线程执行完毕
        countDownLatch.await();
    }
}
