package com.bjsxt.entity;

import java.util.Date;

/**
 * @author pengyu
 */
public class Duty {

    private int dtId;
    private Date dtDate;
    private String signinTime;
    private String signoutTime;

    private String empId;
    private Employee emp;

    public Duty() {
    }

    public Duty(int dtId, Date dtDate, String signinTime, String signoutTime, String empId, Employee emp) {
        this.dtId = dtId;
        this.dtDate = dtDate;
        this.signinTime = signinTime;
        this.signoutTime = signoutTime;
        this.empId = empId;
        this.emp = emp;
    }

    public int getDtId() {
        return dtId;
    }

    public void setDtId(int dtId) {
        this.dtId = dtId;
    }

    public Date getDtDate() {
        return dtDate;
    }

    public void setDtDate(Date dtDate) {
        this.dtDate = dtDate;
    }

    public String getSigninTime() {
        return signinTime;
    }

    public void setSigninTime(String signinTime) {
        this.signinTime = signinTime;
    }

    public String getSignoutTime() {
        return signoutTime;
    }

    public void setSignoutTime(String signoutTime) {
        this.signoutTime = signoutTime;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    @Override
    public String toString() {
        String str =  "Duty{" +
                "dtId=" + dtId +
                ", dtDate=" + dtDate +
                ", signinTime='" + signinTime + '\'' +
                ", signoutTime='" + signoutTime + '\'' +
                ", empId='" + empId + '\'' +
                '}';
        if (emp!=null){
            str+=emp.toString();
        }
        return str;
    }
}
