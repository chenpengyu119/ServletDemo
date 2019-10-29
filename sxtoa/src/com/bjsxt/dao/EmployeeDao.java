package com.bjsxt.dao;

import com.bjsxt.entity.Employee;
import com.bjsxt.util.PageBean;

import java.util.List;

public interface EmployeeDao {

    /**
     * 查询所有上级信息
     */
    public List<Employee> findByEmptype(int empType);

    /**
     * 插入一条员工数据
     * @param emp
     * @return
     */
    public int save(Employee emp);
    public List<Employee> findAll();

    /**
     * 获取第一页数据
     * @return
     */
    public List<Employee> list();

    /**
     * 查询分页数据
     * @param pageBean
     * @return
     */
    public List<Employee> list(PageBean pageBean, Object[] parmas);

    /**
     * 获取总记录数
     */
    public int getTotal();

    public Employee findById(String empId);

    public int update(Employee employee);


}
