package com.restep.juc.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author restep
 * @date 2019/5/17
 */
public class AtomicReferenceTest {
    public static void main(String[] args) {
        AtomicReference<Simple> atomic = new AtomicReference<>(new Simple("restep", 12));
        System.out.println(atomic.get());

        boolean result = atomic.compareAndSet(new Simple("aaa", 10), new Simple("bbb", 23));
        System.out.println(result);
    }

    static class Simple {
        private String name;
        private int age;

        public Simple(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Simple{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
