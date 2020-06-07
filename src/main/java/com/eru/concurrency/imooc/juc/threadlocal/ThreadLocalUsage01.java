package com.eru.concurrency.imooc.juc.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池打印日期
 * Created by eru on 2020/5/31.
 */
@Slf4j
public class ThreadLocalUsage01 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalUsage01().date(10);
                log.info(date);
            }
        });
        service.execute(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalUsage01().date(1007);
                log.info(date);
            }
        });
        service.shutdown();
    }

    public String date(int millSecond){
        long second = millSecond * 1000;
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(second);
        return date;
    }
}
