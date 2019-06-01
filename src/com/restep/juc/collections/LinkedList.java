package com.restep.juc.collections;

/**
 * @author restep
 * @date 2019/5/30
 */
public class LinkedList<E> {
    private Node<E> first;

    private int size;

    public LinkedList() {
        this.first = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return 0 == size();
    }

    public static <E> LinkedList<E> of(E... elements) {
        LinkedList<E> list = new LinkedList<>();
        if (0 != elements.length) {
            for (E ele : elements) {
                list.addFirst(ele);
            }
        }

        return list;
    }

    public LinkedList<E> addFirst(E e) {
        final Node<E> newNode = new Node<>(e);
        newNode.next = first;
        this.size++;
        this.first = newNode;

        return this;
    }

    public E removeFirst() {
        if (this.isEmpty()) {
            throw new NoElementException("the linkedList is empty");
        }

        Node<E> temp = first;
        first = first.next;
        size--;

        return temp.value;
    }

    public boolean contains(E e) {
        Node<E> current = first;
        while (null != current) {
            if (current.value == e) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder("[");
        Node<E> current = first;
        while (null != current) {
            builder.append(current.toString()).append(",");
            current = current.next;
        }
        builder.replace(builder.length() - 1, builder.length(), "]");
        return builder.toString();
    }

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            if (null != value) {
                return value.toString();
            }

            return "null";
        }
    }

    static class NoElementException extends RuntimeException {
        public NoElementException(String message) {
            super(message);
        }
    }
}
