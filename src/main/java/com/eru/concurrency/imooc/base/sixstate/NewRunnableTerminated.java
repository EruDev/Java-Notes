package com.eru.concurrency.imooc.base.sixstate;

import lombok.extern.slf4j.Slf4j;

/**
 * 演示NEW RUNNABLE Terminated状态
 * Created by eru on 2020/5/28.
 */
@Slf4j
public class NewRunnableTerminated implements Runnable{
    public static void main(String[] args) {
        NewRunnableTerminated runnable = new NewRunnableTerminated();
        Thread thread = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        log.info(thread.getName() + thread.getState());
        log.info(thread2.getName() + thread2.getState());
        thread.start();
        thread2.start();
        log.info(thread.getName() + thread.getState());
        log.info(thread2.getName() + thread2.getState());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(thread.getName() + thread.getState());
        log.info(thread2.getName() + thread2.getState());
    }

    @Override
    public void run() {

    }
}
