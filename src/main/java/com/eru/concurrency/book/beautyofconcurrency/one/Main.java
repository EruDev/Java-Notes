package com.eru.concurrency.book.beautyofconcurrency.one;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by eru on 2020/6/25.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(100);
        new Thread(new Producer(queue), "生产者").start();
        Thread.sleep(10);
        new Thread(new Consumer(queue), "消费者").start();
    }
}
