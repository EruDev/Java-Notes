package com.eru.concurrency.imooc.juc.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 循环打印日期（线程池）
 * Created by eru on 2020/5/31.
 */
@Slf4j
public class ThreadLocalUsage02 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 1000; i++) {
            final int finalI = i;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalUsage02().date(finalI);
                    log.info(date);
                }
            });
        }
        service.shutdown();
    }

    public String date(int millSecond){
        long second = millSecond * 1000;
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(second);
        return date;
    }
}
