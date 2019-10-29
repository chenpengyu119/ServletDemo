package com.bjsxt.test;

import com.bjsxt.entity.Department;
import com.bjsxt.service.DepartmentService;
import com.bjsxt.service.impl.DepartmentServiceImpl;
import org.junit.Test;

import java.util.List;

/**
 * 测试DepartmentService
 */
public class DepartmentTest {
    public DepartmentService deptService = new DepartmentServiceImpl();

    /**
     * 测试添加部门
     */
    @Test
    public void addTest(){
        Department dept = new Department(1, "总裁办", "501");
        int n = deptService.add(dept);
        System.out.println(n);
    }

    @Test
    public void showAllTest(){
        List<Department> deptList = deptService.showAll();
        System.out.println(deptList.size());
    }

    @Test
    public void deleteTest(){
        int n = deptService.delete(1);
        System.out.println(n>0?"删除成功":"删除失败");
    }

    /**
     * 测试根据部门编号查询
     */
    @Test
    public void findById(){
        Department dept = deptService.findById(3);
        System.out.println(dept);
    }

    /**
     * 测试更新
     */
    @Test
    public void updateTest(){
        int n = deptService.update(new Department(4, "开发二部", "108"));
        System.out.println(n);
    }



}
