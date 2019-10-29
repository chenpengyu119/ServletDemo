package com.bjsxt.test.prototype;

import java.util.Date;

/**
 * 浅复制
 */
public class Client {
    public static void main(String[] args) throws CloneNotSupportedException {

        Sheep s1 = new Sheep("少莉", new Date());

        System.out.println(s1);

        System.out.println(s1.getName());
        System.out.println(s1.getBirthday());

        Sheep s2 = (Sheep) s1.clone();
        System.out.println(s2);
        System.out.println(s2.getName());
        System.out.println(s2.getBirthday());
    }
}
