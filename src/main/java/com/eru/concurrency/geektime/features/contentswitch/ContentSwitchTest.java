package com.eru.concurrency.geektime.features.contentswitch;

/**
 * 当count为千万时，单线程和多线程执行耗时差不多，再往上一个级别 多线程耗时短
 * Created by eru on 2020/6/6.
 */
public class ContentSwitchTest {

    private static long count = 10000000L;

    public static void main(String[] args) {
        concurrency();
        serial();
    }

    private static void concurrency() {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (int i = 0; i < count; i++) {
                    a += 5;
                }
                System.out.println("in thread a=" + a);
            }
        });
        thread.start();
        int b = 0;
        for (int i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency :" + time + "ms,b=" + b);
    }

    private static void serial(){
        long start = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < count; i++) {
            a += 5;
        }

        int b = 0;
        for (int i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms,a=" + a + "b=" + b);
    }
}
