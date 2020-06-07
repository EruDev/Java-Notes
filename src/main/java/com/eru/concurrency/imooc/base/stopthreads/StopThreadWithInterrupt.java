package com.eru.concurrency.imooc.base.stopthreads;

import com.eru.concurrency.imooc.base.utils.SleepUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 演示使用 interrupt 来中断线程
 * Created by eru on 2020/5/27.
 */
@Slf4j
public class StopThreadWithInterrupt implements Runnable{
    public static void main(String[] args) {
        Thread t = new Thread(new StopThreadWithInterrupt());
        t.start();
        SleepUtil.seconds(2);
        // 发出中断线程
        t.interrupt();
    }

    @Override
    public void run() {
        int num = 0;

        while (!Thread.currentThread().isInterrupted() && num < Integer.MAX_VALUE / 2){
            if (num % 10000 == 0){
                log.debug(num + "是10000的倍数");
            }
            num++;
        }
    }
}
