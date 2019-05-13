package com.restep.pattern.ch05;

/**
 * @author restep
 * @date 2019/5/14
 */
public class UsePersonThread extends Thread {
    private Person person;

    public UsePersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " print " + person);
        }
    }
}
