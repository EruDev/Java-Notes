package com.eru.concurrency.book.concurrency.three;

import com.eru.concurrency.book.concurrency.annotations.NotThreadSafe;

/**
 * Created by eru on 2020/7/6.
 */
@NotThreadSafe
public class MutablePoint {
    public int x, y;

    public MutablePoint() {
        this.x = 0;
        this.y = 0;
    }

    public MutablePoint(MutablePoint p){
        this.x = p.x;
        this.y = p.y;
    }
}
