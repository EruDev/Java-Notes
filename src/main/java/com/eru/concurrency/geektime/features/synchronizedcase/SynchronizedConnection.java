package com.eru.concurrency.geektime.features.synchronizedcase;

import java.util.Scanner;

/**
 * synchronized保护有关联关系的多个资源 银行转账问题
 * Created by eru on 2020/6/7.
 */
public class SynchronizedConnection {

    // 线程不安全
    class Account{
        private int balance;

        public void transfer(Account target, int amount){
            this.balance -= amount;
            target.balance += amount;
        }
    }

    /** 账户余额 */
    private int balance;
    /** 中断标志 */
    private volatile boolean flag = true;

    private SynchronizedConnection(int balance){
        this.balance = balance;
    }

    public void breakLoop(){
        flag = false;
    }

    // synchronized锁的是当前对象的balance, target不受保护(不是线程安全的)
    public synchronized/***/ void transfer(SynchronizedConnection target, int amount){
        /**synchronized (SynchronizedConnection.class) 可以解决转账问题,不过都变成串行操作了*/
        if (this.balance >= amount) {
            while (flag) {
                // 阻塞, 模拟控制台输入一个标识, 多线程同时去竞争执行
            }

            this.balance -= amount;
            System.out.println(Thread.currentThread().getName() + "balance[" +
                    this.balance + "], target balance[" + target.balance + "]");
            target.balance += amount;
            System.out.println(Thread.currentThread().getName() + "balance[" +
                    this.balance + "], target balance[" + target.balance + "]");
        }
    }

    public static void main(String[] args) {
        SynchronizedConnection accountA = new SynchronizedConnection(200);
        SynchronizedConnection accountB = new SynchronizedConnection(200);
        SynchronizedConnection accountC = new SynchronizedConnection(200);

        new Thread(new Runnable() {
            @Override
            public void run() {
                accountA.transfer(accountB, 100);
            }
        }, "thread1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                accountB.transfer(accountC, 100);
            }
        }, "thread2").start();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String ipt = scanner.next();
            if ("1".equals(ipt)){
                accountA.breakLoop();
                accountB.breakLoop();
                accountC.breakLoop();
                break;
            }
        }
        System.out.println("accountA:" + accountA.balance);
        System.out.println("accountB:" + accountB.balance);
        System.out.println("accountC:" + accountC.balance);
    }
}
