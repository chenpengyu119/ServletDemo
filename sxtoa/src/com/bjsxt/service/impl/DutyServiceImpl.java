package com.bjsxt.service.impl;

import com.bjsxt.dao.DutyDao;
import com.bjsxt.dao.impl.DutyDaoImpl;
import com.bjsxt.entity.Duty;
import com.bjsxt.entity.Employee;
import com.bjsxt.service.DutyService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DutyServiceImpl implements DutyService {
    DutyDao dutyDao = new DutyDaoImpl();

    /**
     *
     * @param empId 员工号
     * @return  n  1：签到成功   0：签到失败    2:已经签到
     */
    @Override
    public String signin(String empId) {

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String today = format.format(new Date());
        // 查询是否已签到
        Duty duty1 = dutyDao.findByEmpIdDate(empId,today);
        if (duty1.getDtDate()==null){
            // 实行签到
            Duty duty = new Duty();
            duty.setEmpId(empId);
            SimpleDateFormat format1 = new SimpleDateFormat("hh:mm:ss");
            // 签到时间
            duty.setSigninTime(format1.format(new Date()));
            // 当前日期
            duty.setDtDate(new java.sql.Date(System.currentTimeMillis()));
            boolean res = dutyDao.save(duty);
            if (res){// 成功
                return "签到成功";
            }else {// 失败
                return "签到失败";
            }

        }else {// 已经签到
            return "已经签到";
        }

    }

    /**
     *
     * @param empId
     * @return  成功 失败 尚未签到
     */
    @Override
    public String signout(String empId) {

        // 查询是否已签到
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String today = format.format(new Date());
        // 查询是否已签到
        Duty duty1 = dutyDao.findByEmpIdDate(empId,today);
        if (duty1==null || duty1.getSigninTime()==null){
            // 尚未签到
            return "尚未签到";
        }else{
            // 开始签退
            Duty duty = new Duty();
            duty.setEmpId(empId);
            SimpleDateFormat format1 = new SimpleDateFormat("hh:mm:ss");
            duty.setSignoutTime(format1.format(new Date()));
            boolean res = dutyDao.update(duty);
            return res?"签退成功":"签退失败";
        }
    }

    @Override
    public List<Duty> list(String empId, int deptNo, java.sql.Date dtDate) {
        return dutyDao.findListByCondition(empId, deptNo, dtDate);
    }
}
