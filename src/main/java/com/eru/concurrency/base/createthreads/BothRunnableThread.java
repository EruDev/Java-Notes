package com.eru.concurrency.base.createthreads;

/**
 * 匿名内部类重写了 run(), 所以是来自Thread
 * Created by eru on 2020/5/26.
 */
public class BothRunnableThread {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我来自Runnable");
            }
        }){
            @Override
            public void run(){
                System.out.println("我来自Thread");
            }
        }.start();
    }
}
