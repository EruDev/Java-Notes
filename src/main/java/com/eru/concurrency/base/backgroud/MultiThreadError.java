package com.eru.concurrency.base.backgroud;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by eru on 2020/5/30.
 */
@Slf4j
public class MultiThreadError implements Runnable{

    private int index = 0;

    private static final MultiThreadError INSTANCE = new MultiThreadError();

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            index++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(INSTANCE);
        Thread thread2 = new Thread(INSTANCE);

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();
        log.info(String.valueOf(INSTANCE.index));
    }
}
