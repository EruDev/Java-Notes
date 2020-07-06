package com.eru.concurrency.book.beautyofconcurrency.one;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by eru on 2020/6/25.
 */
public class Consumer implements Runnable{

    private final BlockingQueue<String> blockingQueue;

    public Consumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        synchronized (blockingQueue){
            while (blockingQueue.size() == 0){
                try {
                    blockingQueue.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            try {
                String item = blockingQueue.take();
                blockingQueue.notifyAll();
                System.out.println(item);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
