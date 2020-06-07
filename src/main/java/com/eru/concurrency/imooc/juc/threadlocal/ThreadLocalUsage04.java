package com.eru.concurrency.imooc.juc.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 利用 ThreadLocal 为每个线程创建 SimpleDateFormat 保证了线程安全
 * Created by eru on 2020/5/31.
 */
@Slf4j
public class ThreadLocalUsage04 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            final int finalI = i;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalUsage04().date(finalI);
                    log.info(date);
                }
            });
        }
        service.shutdown();
    }

    public String date(long millSecond){
        long second = 1000 * millSecond;
        SimpleDateFormat sdf = ThreadSafe.sdf.get();
        return sdf.format(second);
    }
}

class ThreadSafe{
    public static ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
}
