package com.eru.concurrency.geektime.features.synchronizedcase;

import java.util.ArrayList;
import java.util.List;

/**
 * synchronized 解决死锁问题
 * Created by eru on 2020/6/7.
 */
public class SynchronizedResolveDeadLock {

    class Account{
        // Allocator应该为单例
        private Allocator allocator;
        private int balance;

        private void transfer(Account target, int amount){
            // 一次性申请转入账户、转出账户，直到成功
            // 循环等待消耗CPU 故不是最好的方式
            while (!allocator.apply(this, target)){
            }
            try {
                // 锁定转入账户
                synchronized (this){
                    // 锁定转出账户
                    synchronized (target){
                        if (this.balance >= amount){
                            this.balance -= amount;
                            target.balance += amount;
                        }
                    }
                }
            }finally {
                allocator.free(this, target);
            }

        }
    }

    /**
     * 破坏占有切等待条件
     * 加入分配类, 只有在同时拿到两个锁的时候 才可以执行后续操作
     */
    class Allocator{
        private List<Object> als = new ArrayList<>();

        /**
         * 同时申请 from、to两个账户对应的账本
         * @param from 转出账户
         * @param to 转入账户
         * @return boolean
         */
        public boolean apply(Object from, Object to){
            if (als.contains(from) || als.contains(to)){
                return false;
            }else{
                als.add(from);
                als.add(to);
            }
            return true;
        }

        /**
         * 释放from、to账本
         * @param from 转出账户
         * @param to 转入账户
         */
        public void free(Object from, Object to){
            als.remove(from);
            als.remove(to);
        }
    }

    /**
     * 对资源进行排序, 按序申请资源
     */
    static class SortAccount{
        // 序号
        private int id;
        // 余额
        private int balance;

        public SortAccount(int id, int balance) {
            this.id = id;
            this.balance = balance;
        }

        public void transfer(SortAccount target, int amount){
            Object left = amount;
            Object right = this;
            if (this.id < target.id){
                left = this;
                right = target;
            }

            // 先锁序号小的, 在锁序号大的
            synchronized (left){
                synchronized(right){
                    if (this.balance >= amount){
                        this.balance -= amount;
                        target.balance += amount;
                    }
                }
            }
        }

        public static void main(String[] args) {
            SortAccount accountA = new SortAccount(1, 200);
            SortAccount accountB = new SortAccount(2, 200);

            new Thread(() -> {
                accountA.transfer(accountB, 100);
            }).start();

            new Thread(() -> {
                accountB.transfer(accountA, 100);
            }).start();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ACCOUNT A balance=" + accountA.balance);
            System.out.println("ACCOUNT B balance=" + accountB.balance);
        }
    }

    /**
     * 等待-通知
     */
    static class WaitNotifyAllAllocator{
        private List<Object> als = new ArrayList<>();

        public synchronized boolean apply(Object from, Object to){
            if (als.contains(from) || als.contains(to)){
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            als.add(from);
            als.add(to);
            return true;
        }

        public synchronized void free(Object from, Object to){
            als.remove(from);
            als.remove(to);
            notifyAll();
        }
    }

    static class AccountTest{
        private int balance;
        // 应该为单例
        private WaitNotifyAllAllocator allocator;

        public AccountTest(int balance, WaitNotifyAllAllocator allocator) {
            this.balance = balance;
            this.allocator = allocator;
        }

        public void transfer(AccountTest target, int amount){
            try {
                while (allocator.apply(this, target)){
                    if (this.balance >= amount){
                        this.balance -= amount;
                        target.balance += amount;
                    }
                }
            }finally {
                allocator.free(this, target);
            }

        }

        public static void main(String[] args) {
            WaitNotifyAllAllocator allocator = new WaitNotifyAllAllocator();
            AccountTest a = new AccountTest(200, allocator);
            AccountTest b = new AccountTest(200, allocator);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    a.transfer(b, 100);
                }
            }).start();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A:" + a.balance);
            System.out.println("B:" + b.balance);
        }
    }
}
