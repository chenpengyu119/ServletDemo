package com.bjsxt.test;

import com.bjsxt.entity.Duty;
import com.bjsxt.service.DutyService;
import com.bjsxt.service.impl.DutyServiceImpl;
import org.junit.Test;

import java.util.Dictionary;
import java.util.List;

public class DutyTest {

    DutyService service = new DutyServiceImpl();
    @Test
    public void signinTest(){
        System.out.println(service.signin("gaoqi"));
    }

    @Test
    public void signoutTest(){
        System.out.println(service.signout("gaoqi"));
    }

    @Test
    public void listTest(){

        List<Duty> dutyList = service.list("a", 1, java.sql.Date.valueOf("2019-07-23"));
        for (Duty d:dutyList){
            System.out.println(d);
        }
    }
}
