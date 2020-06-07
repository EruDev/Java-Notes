package com.eru.concurrency.geektime.features.volatilecase;

import java.util.Scanner;

/**
 * Volatile Example
 * Created by eru on 2020/6/7.
 */
public class VolatileExample {

    private static int x = 0;

    private static volatile boolean flag = false;

    public static void reader(){
        if (flag){
            System.out.println(x);
        }
    }

    public static void writer(){
        x = 42;
        flag = true;
    }

    public static void main(String[] args) {
        writer();
        reader();

        Volatile volatileV = new Volatile();
        new Thread(volatileV, "volatile test").start();

        System.out.println(Thread.currentThread().getName() + "正在执行");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String val = scanner.next();
            if ("1".equals(val)){
                new Thread(volatileV::stopThread).start();
                break;
            }
        }

        System.out.println(Thread.currentThread().getName() + "退出了");
    }

    static class Volatile implements Runnable{

        private volatile boolean flag = true;

        @Override
        public void run() {
            while (flag){
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕!");
        }

        public void stopThread(){
            flag = false;
        }
    }
}
