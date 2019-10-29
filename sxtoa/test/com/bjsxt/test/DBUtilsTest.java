package com.bjsxt.test;

import com.bjsxt.entity.Employee;
import com.bjsxt.util.BeanHandler;
import com.bjsxt.util.BeanListHandler;
import com.bjsxt.util.DbUtils;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtilsTest {

    @Test
    public void queryGeneralCallTest(){

        BeanListHandler handler = new BeanListHandler() {
            @Override
            public List resultHandler(ResultSet rs) {
                List<Employee> empList = new ArrayList<>();
                while (true) {
                    try {
                        if (!rs.next()) break;
                        Employee emp = new Employee();
                        emp.setEmpId(rs.getString("empId"));
                        emp.setRealName(rs.getString("realName"));
                        empList.add(emp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return empList;
            }
        };
        String sql = "select * from employee";
        List<Employee> empList = DbUtils.queryGeneralCall(sql, handler);
        System.out.println("查询成功");
        for (Employee e:empList){
            System.out.println(e);
        }
    }

    @Test
    public void queryOneGeneralCall(){
        BeanHandler handler = new BeanHandler() {
            @Override
            public Object resultHandler(ResultSet rs) {
                Employee emp = new Employee();
                try {
                    if (rs.next()){
                        emp.setRealName(rs.getString("realName"));
                        emp.setEmpId(rs.getString("empId"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return emp;
            }
        };
        String sql = "select * from employee where empId = ?";

        Employee emp = DbUtils.queryOneGeneralCall(sql, handler, 2);
        System.out.println(emp);
    }


}
