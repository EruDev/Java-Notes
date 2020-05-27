package com.eru.concurrency.base.startthread;

/**
 * run、start 启动线程
 * Created by eru on 2020/5/27.
 */
public class RunAndStartThread {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println("runnable...");
        };
        runnable.run();
        new Thread(() -> System.out.println("start...")).start();
    }
}
