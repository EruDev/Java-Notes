package com.eru.sourcecode.one;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * LinkedList Demo
 * Created by eru on 2020/6/16.
 */
public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        //String s = list.get(3);
        //System.out.println(s);
        ListIterator<String> listIterator = list.listIterator(3);
        while (listIterator.hasPrevious()){
            String next = listIterator.previous();
            System.out.println(next);
        }
    }
}
