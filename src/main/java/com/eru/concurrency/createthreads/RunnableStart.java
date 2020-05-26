package com.eru.concurrency.createthreads;

/**
 * 实现 Runnable 接口, 来启动线程
 * Created by eru on 2020/5/26.
 */
public class RunnableStart implements Runnable{

    public static void main(String[] args) {
        Thread runnableThread = new Thread(new RunnableStart());
        runnableThread.start();
    }

    @Override
    public void run() {
        System.out.println("start by implement Runnable");
    }
}
