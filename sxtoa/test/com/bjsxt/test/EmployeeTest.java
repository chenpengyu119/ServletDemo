package com.bjsxt.test;

import com.bjsxt.dao.EmployeeDao;
import com.bjsxt.dao.impl.EmployeeDaoImpl;
import com.bjsxt.entity.Employee;
import com.bjsxt.service.EmployeeService;
import com.bjsxt.service.impl.EmployeeServiceImpl;
import com.bjsxt.util.BeanListHandler;
import com.bjsxt.util.DbUtils;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTest {

    private EmployeeDao empDao = new EmployeeDaoImpl();
    private EmployeeService empService = new EmployeeServiceImpl();

    @Test
    public void findByEmptypeTest(){
        List<Employee> empList = empDao.findByEmptype(2);
        System.out.println("开始-------------");
        for (Employee e:empList){
            System.out.println(e);
        }
    }

    @Test
    public void addTest() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int n = empDao.save(new Employee("6", "0", 1, 1, "admin", "赵子龙", "女",new java.sql.Date(( format.parse("2019-07-20").getTime())), new java.sql.Date(format.parse("2019-07-20").getTime()), new java.sql.Date(format.parse("2019-07-20").getTime()), 1, "15524612", 2, "111122", "1123", "122255525",null,null));
        System.out.println(n>0?"插入成功":"插入失败");
    }

    @Test
    public void findAllTest(){
        List<Employee> empList = empDao.findAll();
        for (Employee emp:empList){
            System.out.println(emp);
        }
    }

    @Test
    public void listTest(){
        List<Employee> empList = new EmployeeDaoImpl().list();
        for (Employee e:empList){
            System.out.println(e);
        }
    }

    @Test
    public void getTotalTest(){
        int total = new EmployeeDaoImpl().getTotal();
        System.out.println(total);
    }

    @Test
    public void updateTest(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int n = empService.update(new Employee("6", "0", 1, 1, "admin", "赵子龙", "女",new java.sql.Date(( format.parse("2019-07-20").getTime())), new java.sql.Date(format.parse("2019-07-20").getTime()), new java.sql.Date(format.parse("2019-07-20").getTime()), 1, "15524612", 2, "111122", "1123", "122255525",null,null));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
