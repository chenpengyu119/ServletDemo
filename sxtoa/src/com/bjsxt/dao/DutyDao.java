package com.bjsxt.dao;

import com.bjsxt.entity.Duty;
import com.bjsxt.entity.Employee;

import java.util.List;

public interface DutyDao {

    /**
     * 条件查找
     * @param empId
     * @param today
     * @return
     */
    public Duty findByEmpIdDate(String empId,String today);

    /**
     * 添加数据
     * @param duty
     * @return
     */
    public boolean save(Duty duty);

    /**
     * 更新数据
     * @param duty
     * @return
     */
    public boolean update(Duty duty);

    public List<Duty> findListByCondition(String empId,int deptNo,java.sql.Date dtDate);
}
