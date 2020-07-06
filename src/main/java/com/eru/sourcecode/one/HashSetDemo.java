package com.eru.sourcecode.one;

import com.alibaba.fastjson.JSON;
import com.sun.media.sound.SoftTuning;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by eru on 2020/6/20.
 */
public class HashSetDemo {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        HashSet<Object> set = new HashSet<>(list);
        System.out.println();
    }
}
