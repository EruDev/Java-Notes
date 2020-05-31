package com.eru.concurrency.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示OOM，设置vm options: -Xmx8m -Xms8m
 * Created by eru on 2020/5/31.
 */
public class FixedThreadPoolOOM {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(1);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            service.execute(new Task());
        }
    }
    static class Task implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


