package com.bjsxt.service.impl;

import com.bjsxt.dao.EmployeeDao;
import com.bjsxt.dao.impl.EmployeeDaoImpl;
import com.bjsxt.entity.Employee;
import com.bjsxt.service.EmployeeService;
import com.bjsxt.util.PageBean;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao empDao = new EmployeeDaoImpl();
    @Override
    public List<Employee> findByEmptype(int empType) {
        return empDao.findByEmptype(empType);
    }

    @Override
    public int add(Employee emp) {
        return empDao.save(emp);
    }

    @Override
    public List<Employee> findAll() {
        return empDao.findAll();
    }

    @Override
    public void list(PageBean pageBean, Object[] params) {

        // 获取总记录数
        int totalCount = getTotal();
        pageBean.setTotalCount(totalCount);
        empDao.list(pageBean, params);
    }

    @Override
    public int getTotal() {
        return empDao.getTotal();
    }

    @Override
    public List<Employee> list() {
        return empDao.list();
    }

    @Override
    public Employee findById(String empId) {
        return empDao.findById(empId);
    }

    @Override
    public int update(Employee emp) {
        return empDao.update(emp);
    }

    @Override
    public Employee login(String empId, String pasword) {

        Employee emp = empDao.findById(empId);
        if (emp==null){
            // 用户名错误
            return emp;
        }else if (emp.getPassword()!=null && emp.getPassword().equals(pasword)){
            return emp;
        }else {
            // 密码错误
            return null;
        }
    }
}
