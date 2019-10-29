package com.bjsxt.service.impl;

import com.bjsxt.dao.DepartmentDao;
import com.bjsxt.dao.impl.DepartmentDaoImpl;
import com.bjsxt.entity.Department;
import com.bjsxt.service.DepartmentService;

import java.util.List;

/**
 * @author pengyu
 */
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDao departmentDao = new DepartmentDaoImpl();
    @Override
    public int add(Department dept) {
        return departmentDao.save(dept);
    }

    @Override
    public List<Department> showAll() {
        return departmentDao.showAll();
    }

    @Override
    public int delete(int deptNo) {
        return departmentDao.delete(deptNo);
    }

    @Override
    public Department findById(int deptNo) {
        return departmentDao.findById(deptNo);
    }

    @Override
    public int update(Department dept) {
        return departmentDao.update(dept);
    }


}
