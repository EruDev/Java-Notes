package com.eru.concurrency.juc.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

/**
 * 两个线程打印日期
 * Created by eru on 2020/5/31.
 */
@Slf4j
public class ThreadLocalUsage00 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalUsage00().date(10);
                log.info(date);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalUsage00().date(1007);
                log.info(date);
            }
        }).start();
    }

    public String date(int millSecond){
        long second = millSecond * 1000;
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(second);
        return date;
    }
}
