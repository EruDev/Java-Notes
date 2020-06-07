package com.eru.concurrency.imooc.juc.threadlocal;

/**
 * ThreadLocal 空指针问题
 * Created by eru on 2020/5/31.
 */
public class ThreadLocalNPE {
    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();

    public void set(){
        longThreadLocal.set(Thread.currentThread().getId());
    }

    public long get(){
        return longThreadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
        threadLocalNPE.get(); // NPE, 因为get 返回null, ThreadLocal<Long> 存的是包装类型, (null)向下转型报NPE
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalNPE.set();
                System.out.println(threadLocalNPE.get());
            }
        }).start();
    }
}
