package com.restep.juc.collections;

/**
 * @author restep
 * @date 2019/5/30
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<String> list = LinkedList.of("aaa", "bbb", "ccc", "ddd");
        System.out.println(list);

        list.addFirst("eee");
        System.out.println(list);

        list.addFirst("eee").addFirst("fff").addFirst("ggg").removeFirst();
        System.out.println(list);

        System.out.println(list.contains("fff"));
    }
}
