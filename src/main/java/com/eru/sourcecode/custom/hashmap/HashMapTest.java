package com.eru.sourcecode.custom.hashmap;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * Created by eru on 2020/6/19.
 */
public class HashMapTest {
    public static void main(String[] args) {
        List<Integer> reverse = Lists.reverse(Lists.newArrayList(1, 2, 3));
        System.out.println(JSON.toJSONString(reverse));
    }
}
