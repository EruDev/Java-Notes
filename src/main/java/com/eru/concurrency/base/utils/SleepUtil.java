package com.eru.concurrency.base.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Created by eru on 2020/5/27.
 */
@Slf4j
public class SleepUtil {

    public static void seconds(long second){
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            log.error("sleep error" + e);
        }
    }
}
