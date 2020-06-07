package com.eru.concurrency.geektime.features.threadstate;

import com.eru.concurrency.geektime.util.ThreadDumpHelper;
import com.eru.concurrency.imooc.base.threadobjectclasscommonmethod.Wait;

/**
 * 演示线程各个状态
 * Created by eru on 2020/6/7.
 */
public class ThreadState {

    static class TimedWaiting implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    static class Waiting implements Runnable{

        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable{

        @Override
        public void run() {
            while (true){
                synchronized (Blocked.class){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static ThreadDumpHelper threadDumpHelper = new ThreadDumpHelper();

    public static void main(String[] args) {
        new Thread(new TimedWaiting(), "TimedWaiting").start();
        new Thread(new Waiting(), "Waiting").start();
        new Thread(new Blocked(), "Blocked1").start();
        new Thread(new Blocked(), "Blocked2").start();
        threadDumpHelper.tryThreadDump();
        System.out.println(111);
    }
}
