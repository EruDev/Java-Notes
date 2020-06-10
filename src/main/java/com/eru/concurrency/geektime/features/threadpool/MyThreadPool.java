package com.eru.concurrency.geektime.features.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 简化版的线程池, 用来描述其工作原理
 * Created by eru on 2020/6/10.
 */
public class MyThreadPool {

    /**
     * 利用阻塞队列实现生产者-消费者模式
     */
    private BlockingQueue<Runnable> workerQueue;

    /**
     * 用来保存工作线程
     */
    private List<WorkerThread> workerThreads = new ArrayList<>();
    
    private MyThreadPool(int size, BlockingQueue<Runnable> blockingQueue){
        this.workerQueue = blockingQueue;
        for (int i = 0; i < size; i++) {
            WorkerThread workerThread = new WorkerThread();
            workerThread.start();
            workerThreads.add(workerThread);
        }
    }

    /**
     * 提交任务
     * @param command
     */
    public void execute(Runnable command){
        try {
            workerQueue.put(command);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    /**
     * 工作线程负责消费任务，并执行任务
     */
    class WorkerThread extends Thread{
        @Override
        public void run() {
            // 循环取任务并执行
            while(true){
                try{
                    Runnable task = workerQueue.take();
                    task.run();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }

            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        MyThreadPool myThreadPool = new MyThreadPool(10, workQueue);

        myThreadPool.execute(() -> System.out.println("hello"));
    }
}
