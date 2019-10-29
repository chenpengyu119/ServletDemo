package com.bjsxt.service;

import com.bjsxt.entity.Duty;

import java.util.List;

public interface DutyService {

    public String signin(String empId);

    /**
     *
     * @param empId
     * @return 1成功 0失败 2尚未签到
     */
    public String signout(String empId);

    public List<Duty> list(String empId,int deptNo,java.sql.Date dtDate);
}
