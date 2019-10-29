package com.bjsxt.dao.impl;

import com.bjsxt.dao.DutyDao;
import com.bjsxt.entity.Department;
import com.bjsxt.entity.Duty;
import com.bjsxt.entity.Employee;
import com.bjsxt.util.BeanHandler;
import com.bjsxt.util.BeanListHandler;
import com.bjsxt.util.DbUtils;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DutyDaoImpl implements DutyDao {
    @Override
    public Duty findByEmpIdDate(String empId,String today) {

        BeanHandler  hander = new BeanHandler() {
            @Override
            public Object resultHandler(ResultSet rs) {
                Duty duty = new Duty();
                try {
                    if (rs.next()){
                        duty.setDtDate(rs.getDate("dtdate"));
                        duty.setEmpId(rs.getString("empId"));
                        duty.setSigninTime(rs.getString("SIGNINTIME"));
                        duty.setSignoutTime(rs.getString("SIGNOUTTIME"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return duty;
            }
        };
        Object[] params = {empId,today};
        String sql = "select * from duty where empId=? and dtdate = ?";
        Duty duty = DbUtils.queryOneGeneralCall(sql, hander,params);

        return duty;
    }

    /**
     * 添加一条记录
     * @param duty
     * @return
     */
    @Override
    public boolean save(Duty duty) {
        String sql = "insert into duty values(null,?,?,?,?)";
        Object[] params = {duty.getEmpId(),duty.getDtDate(),duty.getSigninTime(),null};
        int n = DbUtils.update(sql, params);
        return n>0?true:false;
    }

    /**
     * 修改数据
     * @param duty
     * @return
     */
    @Override
    public boolean update(Duty duty) {

        String sql = "update duty set SIGNOUTTIME = ? where empId= ?";
        Object[] params = {duty.getSignoutTime(),duty.getEmpId()};
        int n = DbUtils.update(sql, params);
        return n>0?true:false;
    }

    @Override
    public List<Duty> findListByCondition(String empId, int deptNo, Date dtDate) {

        BeanListHandler handler = new BeanListHandler() {
            @Override
            public List resultHandler(ResultSet rs) {
                List<Duty> dutyList = new ArrayList<>();
                while (true){
                    try {
                        if (!rs.next()) {
                            break;
                        }
                        Duty duty = new Duty();
                        duty.setEmpId(rs.getString("empId"));
                        duty.setDtDate(rs.getDate("dtDate"));
                        duty.setSigninTime(rs.getString("signinTime"));
                        duty.setSignoutTime(rs.getString("signoutTime"));
                        Employee emp = new Employee();
                        emp.setRealName(rs.getString("realName"));
                        Department dept = new Department();
                        dept.setDeptName(rs.getString("deptName"));
                        emp.setDept(dept);
                        duty.setEmp(emp);
                        dutyList.add(duty);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return dutyList;
            }
        };

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("select * from duty dt\n" +
                "join employee emp\n" +
                "on dt.EMPID = emp.EMPID\n" +
                "JOIN dept d\n" +
                "on d.DEPTNO = emp.DEPTNO ");
        if (empId!=null&&!"".equals(empId)){
            sqlBuilder.append("  where emp.EMPID like '%"+empId+"%' ");
        }
        if (deptNo!=0){
            sqlBuilder.append(" and d.DEPTNO = "+deptNo+" ");
        }
        if (dtDate!=null){
            sqlBuilder.append(" and dt.DTDATE = '"+dtDate+"' ");
        }

        String sql = sqlBuilder.toString();
        List<Duty> dutyList = DbUtils.queryGeneralCall(sql, handler);

        return dutyList;
    }
}
