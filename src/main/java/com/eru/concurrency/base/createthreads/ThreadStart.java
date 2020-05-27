package com.eru.concurrency.base.createthreads;

/**
 * 继承 Thread 来启动线程
 * Created by eru on 2020/5/26.
 */
public class ThreadStart extends Thread{
    public static void main(String[] args) {
        ThreadStart thread = new ThreadStart();
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("extend Thread");
    }
}
