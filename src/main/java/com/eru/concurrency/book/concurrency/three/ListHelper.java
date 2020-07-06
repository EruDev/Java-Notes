package com.eru.concurrency.book.concurrency.three;

import com.eru.concurrency.book.concurrency.annotations.NotThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by eru on 2020/7/6.
 */
@NotThreadSafe
public class ListHelper<E> {
    private final List<E> list = Collections.synchronizedList(new ArrayList<E>());

    /***
     * 其实不是线程安全的，synchronized 同步是同步的 ListHelper
     * @param e
     * @return
     */
    public synchronized boolean putIfAbsent(E e){
        boolean absent = !list.contains(e);
        if (absent){
            list.add(e);
        }
        return absent;
    }
}
