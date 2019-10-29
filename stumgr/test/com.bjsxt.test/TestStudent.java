package com.bjsxt.test;

import com.bjsxt.dao.StudentDao;
import com.bjsxt.dao.impl.StudentDaoImpl;
import org.junit.Test;

public class TestStudent {
    StudentDao dao = new StudentDaoImpl();
    @Test
    public void findCountTest(){
        int n= dao.findCount("文", 0);
        System.out.println(n);
    }
}
