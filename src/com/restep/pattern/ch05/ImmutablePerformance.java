package com.restep.pattern.ch05;

/**
 * 不可变对象 可变对象 性能测试
 *
 * @author restep
 * @date 2019/5/14
 */
public class ImmutablePerformance {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        //22439
        SyncObj syncObj = new SyncObj();
        syncObj.setName("restep");

        //22331
        //ImmutableObj immutableObj = new ImmutableObj("restep");

        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    System.out.println(syncObj.toString());
                }
            }
        };
        t1.start();

        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    System.out.println(syncObj.toString());
                }
            }
        };
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        long end = System.currentTimeMillis();

        System.out.println("elapsed time: " + (end - start));
    }
}

final class ImmutableObj {
    private final String NAME;

    public ImmutableObj(String NAME) {
        this.NAME = NAME;
    }

    @Override
    public String toString() {
        return "ImmutableObj{" +
                "NAME='" + NAME + '\'' +
                '}';
    }
}

class SyncObj {
    private String name;

    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public synchronized String toString() {
        return "SyncObj{" +
                "name='" + name + '\'' +
                '}';
    }
}
