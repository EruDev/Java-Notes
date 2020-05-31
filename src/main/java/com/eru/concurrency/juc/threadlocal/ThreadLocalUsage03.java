package com.eru.concurrency.juc.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 这里采用实例化一个SimpleDateFormat + sync, 保证打印的日期不会有问题
 * Created by eru on 2020/5/31.
 */
@Slf4j
public class ThreadLocalUsage03 {

    private static SimpleDateFormat INSTANCE;

    private ThreadLocalUsage03(){
        INSTANCE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 1000; i++) {
            final int finalI = i;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalUsage03().date(finalI);
                    log.info(date);
                }
            });
        }
        service.shutdown();
    }

    public synchronized String date(int millSecond){
        long second = millSecond * 1000;
        String date = INSTANCE.format(second);
        return date;
    }
}
