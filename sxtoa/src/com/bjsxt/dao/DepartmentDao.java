package com.bjsxt.dao;

import com.bjsxt.entity.Department;

import java.util.List;

/**
 * 部门
 * @author pengyu
 */
public interface DepartmentDao {
    /**
     * 添加一个部门
     * @param dept
     * @return  n  n>0 添加成功
     */
    public int save(Department dept);

    /**
     * 查询所有用户
     * @return list 部门List
     */
    public List<Department> showAll();

    /**
     *
     * @param deptNo
     * @return n n>0 表明删除成功
     */
    public int delete(int deptNo);

    /**
     * 根据部门编号查询部门信息
     * @param deptNo
     * @return
     */
    public Department findById(int deptNo);

    /**
     * 更新部门信息
     * @param dept
     * @return
     */
    public int update(Department dept);
}
