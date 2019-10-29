package com.bjsxt.service;

import com.bjsxt.entity.Employee;
import com.bjsxt.util.PageBean;

import java.util.List;

/**
 * @author pengyu
 */
public interface EmployeeService {
    /**
     * 查询emptype为2的员工
     * @return
     */
    public List<Employee> findByEmptype(int empType);

    /**
     * 插入一条员工记录
     * @param emp
     * @return
     */
    public int add(Employee emp);

    /**
     * 查询所有员工
     */
    public List<Employee> findAll();

    /**
     * 查询分页数据
     * @param pageBean
     * @return
     */
    public void list(PageBean pageBean, Object[] params);

    /**
     * 获取总记录数
     */
    public int getTotal();

    /**
     * 获取第一页数据
     * @return
     */
    public List<Employee> list();

    public Employee findById(String empId);

    public int update(Employee emp);

    public Employee login(String empId,String pasword);
}
