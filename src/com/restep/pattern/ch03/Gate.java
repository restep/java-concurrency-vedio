package com.restep.pattern.ch03;

/**
 * @author restep
 * @date 2019/5/13
 */
public class Gate {
    private int counter = 0;
    private String name = "nobody";
    private String address = "nowhere";

    /**
     * 存在共享数据
     * 存在竞争
     * 就会导致线程不安全
     *
     * @param name
     * @param address
     */
    public synchronized void pass(String name, String address) {
        this.counter++;
        this.name = name;
        this.address = address;

        verify();
    }

    private void verify() {
        if (this.name.charAt(0) != this.address.charAt(0)) {
            System.out.println("broken: " + toString());
        }
    }

    @Override
    public synchronized String toString() {
        return "counter: " + counter + " name: " + name + " address: " + address;
    }
}
