package com.eru.concurrency.base.createthreads.wrongways;

import java.util.TimerTask;

/**
 * 定时器创建线程
 * Created by eru on 2020/5/26.
 */
public class Timer {
    public static void main(String[] args) {
        new java.util.Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1000, 1000);
    }
}
