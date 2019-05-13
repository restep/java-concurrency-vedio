package com.restep.pattern.ch03;

/**
 * @author restep
 * @date 2019/5/13
 */
public class GateTest {
    public static void main(String[] args) {
        Gate gate = new Gate();

        Person beijing = new Person("北京人", "北京", gate);
        Person shanghai = new Person("上海人", "上海", gate);
        Person hangzhou = new Person("杭州人", "杭州", gate);

        beijing.start();
        shanghai.start();
        hangzhou.start();
    }
}
