package com.restep.juc.collections;

/**
 * @author restep
 * @date 2019/5/30
 */
public class PriorityLinkedList<E extends Comparable<E>> {
    private Node<E> first;

    private final Node<E> NULL = (Node<E>) null;

    private static final String PLAIN_NULL = "null";

    private int size;

    public PriorityLinkedList() {
        this.first = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return 0 == size();
    }

    public static <E extends Comparable<E>> PriorityLinkedList<E> of(E... elements) {
        PriorityLinkedList<E> list = new PriorityLinkedList<>();
        if (0 != elements.length) {
            for (E ele : elements) {
                list.addFirst(ele);
            }
        }

        return list;
    }

    public PriorityLinkedList<E> addFirst(E e) {
        final Node<E> newNode = new Node<>(e);
        Node<E> previous = NULL;
        Node<E> current = first;
        while (null != current && e.compareTo(current.value) > 0) {
            previous = current;
            current = current.next;
        }

        if (NULL == previous) {
            first = newNode;
        } else {
            previous.next = newNode;
        }

        newNode.next = current;
        size++;

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
