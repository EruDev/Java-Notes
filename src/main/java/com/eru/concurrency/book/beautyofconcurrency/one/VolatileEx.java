package com.eru.concurrency.book.beautyofconcurrency.one;


/**
 * Created by eru on 2020/6/22.
 */
public class VolatileEx {

    public void syn(){
        synchronized (this){
            System.out.println("aaa");
        }
    }
}
