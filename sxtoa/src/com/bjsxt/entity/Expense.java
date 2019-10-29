package com.bjsxt.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Expense {
    // 报销单编号
     private int expId;
     // 报销总金额
     private double totalAmount;
     // 报销时间
     private Date expTime;
     // 总备注信息
    private String expDesc;
    // 下一个审核人
    private String nextAuditor;
    // 最后一次审核结果
    private String lastResult;
    // 审核状态
        /*
        0  新创建
        1 审核中
        2 审核通过
        3 审核拒绝
        4 审核打回
        */
    private String status;

    // 报销人编号
    private String empId;
    // 报销人
    private Employee emp;
    // 报销明细
    List<ExpenseItem> itemList = new ArrayList<>();

    public Expense() {
    }

    public Expense(int expId, double totalAmount, Date expTime, String expDesc, String nextAuditor,
                   String lastResult, String status, String empId, Employee emp, List<ExpenseItem> itemList) {
        this.expId = expId;
        this.totalAmount = totalAmount;
        this.expTime = expTime;
        this.expDesc = expDesc;
        this.nextAuditor = nextAuditor;
        this.lastResult = lastResult;
        this.status = status;
        this.empId = empId;
        this.emp = emp;
        this.itemList = itemList;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getExpTime() {
        return expTime;
    }

    public void setExpTime(Date expTime) {
        this.expTime = expTime;
    }

    public String getExpDesc() {
        return expDesc;
    }

    public void setExpDesc(String expDesc) {
        this.expDesc = expDesc;
    }

    public String getNextAuditor() {
        return nextAuditor;
    }

    public void setNextAuditor(String nextAuditor) {
        this.nextAuditor = nextAuditor;
    }

    public String getLastResult() {
        return lastResult;
    }

    public void setLastResult(String lastResult) {
        this.lastResult = lastResult;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<ExpenseItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<ExpenseItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        String str = "Expense{" +
                "expId=" + expId +
                ", totalAmount=" + totalAmount +
                ", expTime=" + expTime +
                ", expDesc='" + expDesc + '\'' +
                ", nextAuditor='" + nextAuditor + '\'' +
                ", lastResult='" + lastResult + '\'' +
                ", status='" + status + '\'' +
                ", empId='" + empId + '\'' +
                ", itemList=" + itemList +
                '}';
        if (emp!=null){
            str+=emp.toString();
        }
        return str;
    }
}
