package com.bjsxt.entity;


import java.sql.Timestamp;

public class Auditing {

    // 审核编号
    private int auditId;
    // 报销单编号
    private int expId ;
    // 审核人
    private String empId;
    // 结果
    private String result;
    // 审核说明
    private String auditDesc;
    // 审核时间
    private Timestamp time;
    // 报销单
    private Expense expense;
    // 审核人
    private Employee emp;


    public Auditing() {
    }

    public Auditing(int auditId, int expId, String empId, String result, String auditDesc, Timestamp time, Expense expense, Employee emp) {
        this.auditId = auditId;
        this.expId = expId;
        this.empId = empId;
        this.result = result;
        this.auditDesc = auditDesc;
        this.time = time;
        this.expense = expense;
        this.emp = emp;
    }

    public int getAuditId() {
        return auditId;
    }

    public void setAuditId(int auditId) {
        this.auditId = auditId;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAuditDesc() {
        return auditDesc;
    }

    public void setAuditDesc(String auditDesc) {
        this.auditDesc = auditDesc;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    @Override
    public String toString() {
        String str =  "Auditing{" +
                "auditId=" + auditId +
                ", expId=" + expId +
                ", empId='" + empId + '\'' +
                ", result='" + result + '\'' +
                ", time=" + time +
                ", auditDesc=" + auditDesc +
                '}';
        if (expense!=null){
            str+=expense;
        }
        if (emp!=null){
            str+=emp;
        }
        return str;
    }
}
