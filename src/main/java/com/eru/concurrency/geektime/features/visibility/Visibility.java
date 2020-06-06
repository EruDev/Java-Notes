package com.eru.concurrency.geektime.features.visibility;

/**
 * 可见性问题
 * Created by eru on 2020/6/6.
 */
public class Visibility {

    private static long count = 0;

    public void add10k(){
        int idx = 0;
        while (idx++ < 10000){
            count += 1;
        }
    }

    public static long calc() {
        final Visibility visibility = new Visibility();
        Thread t1 = new Thread(() -> visibility.add10k());
        Thread t2 = new Thread(() -> visibility.add10k());

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().isInterrupted();
        }
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(calc());;
    }
}
