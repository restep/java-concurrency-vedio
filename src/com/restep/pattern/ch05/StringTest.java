package com.restep.pattern.ch05;

/**
 * JDK中 String是不可变对象
 * 对String的操作是返回的一个新对象
 *
 * @author restep
 * @date 2019/5/14
 */
public class StringTest {
    public static void main(String[] args) {
        String str = "hello";
        String str2 = str.replace("l", "k");
        System.out.println(str == str2);
    }
}
