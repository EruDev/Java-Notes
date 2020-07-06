package com.eru.concurrency.book.beautyofconcurrency.one;

import java.util.concurrent.BlockingQueue;

/**
 * Created by eru on 2020/6/25.
 */
public class Producer implements Runnable{

    private final BlockingQueue<String> blockingQueue;

    public Producer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        synchronized (blockingQueue){
            while (blockingQueue.size() == 100){
                try {
                    blockingQueue.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        try {
            blockingQueue.add("producing...");
            blockingQueue.notifyAll();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}
