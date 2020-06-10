package com.eru.concurrency.geektime.features.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 循环屏障, 主要是多个线程间相互等待
 * Created by eru on 2020/6/10.
 */
public class CyclicBarrierEx {

    private static class Worker extends Thread{

        private CyclicBarrier cyclicBarrier;

        private Worker(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "开始等待其他线程");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "开始执行");
                // 工作线程, Thread.sleep() 来模拟业务操作耗时
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + "执行完毕");
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        int threadCount = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount);
        for (int i = 0; i < threadCount; i++) {
            Worker worker = new Worker(cyclicBarrier);
            worker.start();
        }
    }
}
