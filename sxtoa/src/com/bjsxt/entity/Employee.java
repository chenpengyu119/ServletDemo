package com.bjsxt.entity;

import java.sql.Date;

/**
 * 员工实体
 * @author pengyu
 */
public class Employee {

    private String empId;
    // 上级编号
    private String mgrId;
    // 职位编号
    private int posId;
    // 部门编号
    private int deptNo;
    // 密码
    private String password;
    // 真实姓名
    private String realName;
    // 性别
    private String sex;
    // 生日
    private Date birthdate;
    // 入职时间
    private Date hiredate;
    // 离职时间
    private Date leavedate;
    // 是否在职 '0-离职  1-在职',
    private int onduty;
    private String qq;
    // 用户类型 1.普通员工  2.管理人员 含经理、总监、总裁等  3.管理员',
    private int empType;
    // 手机号码
    private String phone;
    // 紧急联系人
    private String emerContactPerson;
    // 身份证号
    private String idCard;

    // 部门
    private Department dept;
    // 岗位
    private Position pos;

    public Employee() {
    }

    public Employee(String empId, String mgrId, int posId, int deptNo, String password,
                    String realName, String sex, Date birthdate, Date hiredate, Date leavedate,
                    int onduty, String qq, int empType, String phone, String emerContactPerson,
                    String idCard, Department dept, Position pos) {
        this.empId = empId;
        this.mgrId = mgrId;
        this.posId = posId;
        this.deptNo = deptNo;
        this.password = password;
        this.realName = realName;
        this.sex = sex;
        this.birthdate = birthdate;
        this.hiredate = hiredate;
        this.leavedate = leavedate;
        this.onduty = onduty;
        this.qq = qq;
        this.empType = empType;
        this.phone = phone;
        this.emerContactPerson = emerContactPerson;
        this.idCard = idCard;
        this.dept = dept;
        this.pos = pos;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getMgrId() {
        return mgrId;
    }

    public void setMgrId(String mgrId) {
        this.mgrId = mgrId;
    }

    public int getPosId() {
        return posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Date getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(Date leavedate) {
        this.leavedate = leavedate;
    }

    public int getOnduty() {
        return onduty;
    }

    public void setOnduty(int onduty) {
        this.onduty = onduty;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public int getEmpType() {
        return empType;
    }

    public void setEmpType(int empType) {
        this.empType = empType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmerContactPerson() {
        return emerContactPerson;
    }

    public void setEmerContactPerson(String emerConcactPerson) {
        this.emerContactPerson = emerConcactPerson;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        String str = "";
        if (dept!=null){
            str+=dept.toString();
        }
        if (pos!=null){
            str+=pos.toString();
        }
        str = "Employee{" +
                "empId='" + empId + '\'' +
                ", mgrId='" + mgrId + '\'' +
                ", posId=" + posId +
                ", deptNo=" + deptNo +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthdate=" + birthdate +
                ", hiredate=" + hiredate +
                ", leavedate=" + leavedate +
                ", onduty=" + onduty +
                ", qq='" + qq + '\'' +
                ", empType=" + empType +
                ", phone='" + phone + '\'' +
                ", emerContactPerson='" + emerContactPerson + '\'' +
                ", idCard='" + idCard + '\'' +
                '}'+ "\n";
        return str;
    }
}
