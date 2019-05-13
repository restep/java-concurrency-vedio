package com.restep.pattern.ch03;

/**
 * @author restep
 * @date 2019/5/13
 */
public class Person extends Thread {
    private final String name;

    private final String address;

    private final Gate gate;

    public Person(String name, String address, Gate gate) {
        this.name = name;
        this.address = address;
        this.gate = gate;
    }

    @Override
    public void run() {
        System.out.println(name + " begin");
        while (true) {
            this.gate.pass(name, address);
        }
    }
}
