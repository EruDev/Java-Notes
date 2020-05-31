package com.eru.concurrency.juc.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时执行的线程池 demo
 * Created by eru on 2020/5/31.
 */
public class ScheduledThreadPoolTest {

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        //service.schedule(new Task(), 5L, TimeUnit.SECONDS);
        service.scheduleAtFixedRate(new Task(), 1, 3, TimeUnit.SECONDS);
    }
}
