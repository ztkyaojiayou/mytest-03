package com.rezen.Demo;

import org.junit.Test;
import sun.java2d.pipe.SpanIterator;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: tk.zou
 * @Description:
 * @Date: 2021-09-29 11:52
 * @Version: 1.0.0
 */

public class JavaStream02 {
    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999),
            new Employee("李四", 58, 5555),
            new Employee("王五", 26, 3333),
            new Employee("赵六", 36, 6666),
            new Employee("田七", 12, 8888)
    );

    /**
     *  测试
     */
    @Test
    public void test01() {
        Stream<Employee> stream = employees.stream().filter((e) -> {
            System.out.println("中间操作");
            return e.getAge() > 35;
        });
        //终止操作
        stream.forEach(System.out::println);
    }

    @Test
    public void test03() {
        employees.stream().filter((e) -> {
            System.out.println("短路");
            return e.getSalary() > 5000;
        }).limit(2).forEach(System.out::println);
    }

    @Test
    public void test04() {
        employees.stream().filter((e) -> e.getSalary() > 5000).skip(2).forEach(System.out::println);
        employees.stream().filter((o)->o.getSalary() > 50000).collect(Collectors.toList());
        List<Employee> res = employees.stream().filter((t) -> {
            return t.getAge() > 18;
        }).collect(Collectors.toList());
    }

    @Test
    public void test05() {
        employees.stream().filter((e) -> e.getSalary() > 5000).limit(2).forEach(System.out::println);
    }

    @Test
    public void test06() {
        employees.stream().filter((e) -> e.getSalary() > 5000).limit(2).forEach(System.out::println);
        employees.stream().map(Employee::getName).forEach(System.out::println);
    }
}
