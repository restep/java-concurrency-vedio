package com.restep.basic.ch01;

/**
 * 线程非安全 TODO
 *
 * @author restep
 * @date 2019/5/9
 */
public class TicketWindow extends Thread {
    //柜台
    private final String NAME;
    //最大的票号
    private static final int MAX = 50;
    //当前票号
    private static int current = 1;

    public TicketWindow(String name) {
        NAME = name;
    }

    @Override
    public void run() {
        while (current <= MAX) {
            System.out.println("name: " + NAME + " current: " + current++);

            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TicketWindow ticketWindow01 = new TicketWindow("1号柜台");
        ticketWindow01.start();

        TicketWindow ticketWindow02 = new TicketWindow("2号柜台");
        ticketWindow02.start();

        TicketWindow ticketWindow03 = new TicketWindow("3号柜台");
        ticketWindow03.start();
    }
}
