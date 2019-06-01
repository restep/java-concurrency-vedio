package com.restep.juc.collections;

import java.util.Random;

/**
 * 跳表
 *
 * @author restep
 * @date 2019/5/30
 */
public class SimpleSkipList {
    private static final byte HEAD_NODE = -1;
    private static final byte DATA_NODE = 0;
    private static final byte TAIL_NODE = -1;

    private Node head;
    private Node tail;
    private int size;
    private int height;
    private Random random;

    public SimpleSkipList() {
        this.head = new Node(null, HEAD_NODE);
        this.tail = new Node(null, TAIL_NODE);
        head.right = tail;
        tail.left = head;
        this.random = new Random(System.currentTimeMillis());
    }

    public boolean isEmpty() {
        return 0 == size();
    }

    public int size() {
        return size;
    }

    private Node find(Integer element) {
        return null;
    }


    private static class Node {
        private Integer value;
        private Node up;
        private Node down;
        private Node left;
        private Node right;
        private byte bit;

        public Node(Integer value, byte bit) {
            this.value = value;
            this.bit = bit;
        }

        public Node(Integer value) {
            this(value, DATA_NODE);
        }
    }

}
