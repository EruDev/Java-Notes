package com.eru.concurrency.geektime.features.synchronizedcase;

import java.util.ArrayList;
import java.util.List;

/**
 * synchronized ex
 * Created by eru on 2020/6/7.
 */
public class SynchronizedExample {
    static class X{
        // 修饰非静态方法 synchronized(this)
        synchronized void foo(){
            // 临界区
        }

        // 修饰静态方法 synchronized(X.class)
        synchronized static void bar(){
            // 临界区
        }

        // 修饰代码块
        Object object = new Object();
        void baz(){
            synchronized (object){
                // 临界区
            }
        }

    }

    // 演示synchronized实现原子操作
    static class SafeCal{
        private int count = 0;

        synchronized void addOne(){
            count += 1;
        }

        synchronized int get(){
            return count;
        }
    }

    public static void main(String[] args) {
        SafeCal safeCal = new SafeCal();
        List<Thread> threads = new ArrayList<>(100);

        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    safeCal.addOne();
                }
            });
            threads.add(t);
        }
        threads.forEach(Thread::start);

        // 等待所有线程执行完毕
        for (Thread t: threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(safeCal.get());
    }
}
