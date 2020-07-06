package com.eru.concurrency.book.concurrency.five;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Created by eru on 2020/7/7.
 */
public class BoundedHashSet<T> {
    private final Set<T> set;
    private Semaphore sem;

    public BoundedHashSet(int bound){
        set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
    }

    public boolean add(T t) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(t);
            return wasAdded;
        }finally {
            if (!wasAdded){
                sem.release();
            }
        }
    }

    public boolean remove(Object o){
        boolean wasRemoved = set.remove(o);
        if (wasRemoved){
            sem.release();
        }
        return wasRemoved;
    }
}
