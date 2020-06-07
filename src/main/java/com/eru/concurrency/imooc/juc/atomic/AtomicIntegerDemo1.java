package com.eru.concurrency.imooc.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示：AtomicInteger基本用法
 * Created by eru on 2020/6/2.
 */
public class AtomicIntegerDemo1 implements Runnable{

    private static final AtomicInteger ATOMIC_INTEGER =  new AtomicInteger();

    public void increment(){
        ATOMIC_INTEGER.getAndIncrement();
        //ATOMIC_INTEGER.getAndAdd(2);
    }

    private static volatile int count = 0;

    public void add(){
        count++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20000; i++) {
            increment();
            add();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo1 runnable = new AtomicIntegerDemo1();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("原子类变量：" + ATOMIC_INTEGER.get());
        System.out.println("普通变量：：" + count);
    }


}
