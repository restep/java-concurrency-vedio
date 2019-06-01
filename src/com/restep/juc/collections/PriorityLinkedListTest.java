package com.restep.juc.collections;

/**
 * @author restep
 * @date 2019/5/30
 */
public class PriorityLinkedListTest {
    public static void main(String[] args) {
        PriorityLinkedList<Integer> list = PriorityLinkedList.of(1, 6, 4, 9, 3, 8, 2, 5, 7);
        System.out.println(list);

        list.addFirst(10);
        System.out.println(list);

        list.addFirst(13).addFirst(11).addFirst(12).removeFirst();
        System.out.println(list);

        System.out.println(list.contains(1));
    }
}
