package com.bjsxt.test.prototype;

import java.util.Date;

/**
 * 浅复制
 */
public class Client2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Date date = date = new Date(21231212112L);
        Sheep2 s1 = new Sheep2("少莉",date );

        System.out.println(s1);
        Sheep2 s2 = (Sheep2) s1.clone();
        System.out.println(s1.getName());
        System.out.println(s1.getBirthday());
        date = new Date(12111212112L);
        System.out.println(s1.getBirthday());

        System.out.println(s2);
        System.out.println(s2.getName());
        System.out.println(s2.getBirthday());
    }
}
