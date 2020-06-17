package com.eru.sourcecode.one;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * Arrays demo
 * Created by eru on 2020/6/16.
 */
@Data
@Slf4j
public class ArraysDemo {


    @Test
    public void binarySearchTest(){
        List<SortDto> list = ImmutableList.of(
                new SortDto("100"),
                new SortDto("300"),
                new SortDto("50"),
                new SortDto("200")
        );
        SortDto[] array = new SortDto[list.size()];
        list.toArray(array);

        log.info("排序之前:{}", JSON.toJSONString(array));
        Arrays.sort(array, Comparator.comparing(SortDto::getSortTarget));
        log.info("搜索前, 排序后：{}", JSON.toJSONString(array));

        int index = Arrays.binarySearch(array, new SortDto("200"), Comparator.comparing(SortDto::getSortTarget));

        if (index < 0){
            throw new RuntimeException("200未找到");
        }

        log.info(String.valueOf(array[index]));
    }

    @Test
    public void sortTest(){
        List<SortDto> list = ImmutableList.of(
                new SortDto("300"),
                new SortDto("200"),
                new SortDto("100")
        );
        SortDto[] array = new SortDto[list.size()];
        list.toArray(array);

        log.info("排序之前：{}", JSON.toJSONString(array));
        Arrays.sort(array, Comparator.comparing(SortDto::getSortTarget));
        log.info("排序之后：{}", JSON.toJSONString(array));
    }


    @Data
    class SortDto{
        private String sortTarget;

        public SortDto(String target){
            this.sortTarget = target;
        }
    }

    @Data
    class SortDto1 implements Comparable<SortDto1>{
        private String sortTarget;

        public SortDto1(String target){
            this.sortTarget = target;
        }

        @Override
        public int compareTo(SortDto1 o) {
            return sortTarget.compareTo(o.sortTarget);
        }
    }

    @Test
    public void maxTest(){
        Collection<SortDto1> list = ImmutableList.of(
                new SortDto1("300"),
                new SortDto1("200"),
                new SortDto1("100")
        );

        SortDto1 maxSortDto = Collections.max(list);
        log.info(JSON.toJSONString(maxSortDto));
    }
}
