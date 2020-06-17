package com.eru.sourcecode.custom.linkedList;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by eru on 2020/6/17.
 */
public class MyLinkedListTest {

    @Test
    public void addTest(){
        MyLinkedList<String> linkedList = new MyLinkedList<>();

        linkedList.addLast("a");
        linkedList.addLast("b");
        linkedList.addLast("c");

        String s = linkedList.get(1);
        Assert.assertEquals("b", s);
    }
}
