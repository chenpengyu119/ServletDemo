package com.bjsxt.test;

import com.bjsxt.dao.StudentDao;
import com.bjsxt.dao.impl.StudentDaoImpl;
import com.bjsxt.entity.Student;
import org.junit.Test;

public class StudentTest {

    private StudentDao studentDao = new StudentDaoImpl();
    @Test
    public void findByIdTest(){
        Student stu = studentDao.findById(6);
        System.out.println(stu);
    }
    @Test
    public void findAllTest(){
        studentDao.findAll();
    }
}
