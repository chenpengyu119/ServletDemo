package com.bjsxt.test;

import com.bjsxt.pojo.People;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ac = null;
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        People people = ac.getBean("peo", People.class);
        System.out.println(people );
    }
}
