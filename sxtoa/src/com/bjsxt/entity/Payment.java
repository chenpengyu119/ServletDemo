package com.bjsxt.entity;

import java.util.Date;

/**
 * @author pengyu
 */
public class Payment{

    private int pidl;
    private double amount;
    private Date payTime;

    private int expId;
    // 报销人
    private String empId;
    // 审核人
    private String payEmpId;

    private Employee emp;
    private Employee payEmp;
    private  Expense exp;

    public Payment() {
    }

    public Payment(int pidl, double amount, Date payTime, int expId, String empId, String payEmpId) {
        this.pidl = pidl;
        this.amount = amount;
        this.payTime = payTime;
        this.expId = expId;
        this.empId = empId;
        this.payEmpId = payEmpId;
    }

    public int getPidl() {
        return pidl;
    }

    public void setPidl(int pidl) {
        this.pidl = pidl;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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

    public String getPayEmpId() {
        return payEmpId;
    }

    public void setPayEmpId(String payEmpId) {
        this.payEmpId = payEmpId;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public Employee getPayEmp() {
        return payEmp;
    }

    public void setPayEmp(Employee payEmp) {
        this.payEmp = payEmp;
    }

    public Expense getExp() {
        return exp;
    }

    public void setExp(Expense exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {

        String str =  "Payment{" +
                "pidl=" + pidl +
                ", amount=" + amount +
                ", payTime=" + payTime +
                ", expId=" + expId +
                ", empId='" + empId + '\'' +
                ", payEmpId='" + payEmpId + '\'' +
  /*              ", emp=" + emp +
                ", payEmp=" + payEmp +
                ", exp=" + exp +*/
                '}';
                if (emp!=null){
                    str+=emp.toString();
                }
                if (payEmp!=null){
                    str+=payEmp.toString();
                }
                if (exp!=null){
                    str+=exp.toString();
                }
                return str;
    }
}
