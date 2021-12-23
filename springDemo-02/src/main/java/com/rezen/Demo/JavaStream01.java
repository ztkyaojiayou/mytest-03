package com.rezen.Demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: tk.zou
 * @Description:
 * @Date: 2021-09-27 15:34
 * @Version: 1.0.0
 */


public class JavaStream01 {
    public static void main(String[] args) {
        //先创建流
        Stream<String> myStream = Stream.of("aaa", "bbb", "ccc", "bbb");

        //收集流中的数据
        //1.收集到list
        List<String> list = myStream.collect(Collectors.toList());
        System.out.println(list);
        //2.收集到set--去重
        Set<String> set = myStream.collect(Collectors.toSet());
        System.out.println(set);
        //收集流中的数据到指定的集合中--list
        ArrayList<String> myList = myStream.collect(Collectors.toCollection(ArrayList::new));
        System.out.println(myList);
        //收集流中的数据到指定的集合中--set
        HashSet<String> mySet = myStream.collect(Collectors.toCollection(HashSet::new));
        System.out.println(mySet);
        //myStream.filter()

    }
}
