package com.eru.concurrency.book.concurrency.three;

import com.eru.concurrency.book.concurrency.annotations.NotThreadSafe;
import com.eru.concurrency.book.concurrency.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by eru on 2020/7/6.
 */
@ThreadSafe
public class ListHelper2<E> {
    private final List<E> list = Collections.synchronizedList(new ArrayList<E>());

    /***
     * 其实不是线程安全的，synchronized 同步是同步的 ListHelper
     * @param e
     * @return
     */
    public boolean putIfAbsent(E e){
        synchronized (list){
            boolean absent = !list.contains(e);
            if (absent){
                list.add(e);
            }
            return absent;
        }
    }
}
