package com.bjsxt.service;

import com.bjsxt.entity.Department;

import java.util.List;

/**
 * @author pengyu
 */
public interface DepartmentService {
    /**
     * 添加一个部门
     * @param dept
     * @return n n>0 添加成功
     */
    public int add(Department dept);

    /**
     * 查询所有部门
     * @return  list 部门List
     */
    public List<Department> showAll();

    /**
     * 根据部门编号删除某个部门
     * @param deptNo
     * @return n  n>0 表示删除成功
     */
    public int delete(int deptNo);

    public Department findById(int deptNo);

    public int update(Department dept);
}
