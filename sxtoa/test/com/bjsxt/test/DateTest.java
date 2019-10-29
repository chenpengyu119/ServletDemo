package com.bjsxt.test;

import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

    @Test
    public void timeStampTest(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String dateDtr = df.format(ts);
        System.out.println(dateDtr);
    }
}
