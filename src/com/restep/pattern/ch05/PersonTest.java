package com.restep.pattern.ch05;

import java.util.stream.IntStream;

/**
 * 不可变对象设计模式
 *
 * @author restep
 * @date 2019/5/14
 */
public class PersonTest {
    public static void main(String[] args) {
        Person person = new Person("restep", "武汉");

        IntStream.range(0, 5).forEach(i ->
                new UsePersonThread(person).start()
        );
    }
}
