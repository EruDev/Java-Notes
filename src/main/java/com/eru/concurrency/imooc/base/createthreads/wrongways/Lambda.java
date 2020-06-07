package com.eru.concurrency.imooc.base.createthreads.wrongways;

import javafx.util.Builder;

/**
 * Lambda创建线程
 * Created by eru on 2020/5/26.
 */
public class Lambda {
    public static void main(String[] args) {
        Builder<Thread> t = () -> new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        t.build().start();
    }
}
