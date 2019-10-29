package com.bjsxt.dao.impl;

import com.bjsxt.dao.EmployeeDao;
import com.bjsxt.entity.Department;
import com.bjsxt.entity.Employee;
import com.bjsxt.entity.Position;
import com.bjsxt.util.BeanHandler;
import com.bjsxt.util.BeanListHandler;
import com.bjsxt.util.DbUtils;
import com.bjsxt.util.PageBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author pengyu
 */
public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public List<Employee> findByEmptype(int empType) {
        // 检索emptype=2的员工
        String sql = "select * from employee where emptype="+empType;

        BeanListHandler<Employee> handler = new BeanListHandler<Employee>() {
            @Override
            public List<Employee> resultHandler(ResultSet rs) {
                List<Employee> empList =  new ArrayList<>();
                while (true){
                    try {
                        if (!rs.next()) {
                            break;
                        }
                        Employee employee = new Employee();
                        employee.setEmpId(rs.getString("empId"));
                        employee.setMgrId(rs.getString("emp_empid"));
                        employee.setPosId(rs.getInt("posId"));
                        employee.setDeptNo(rs.getInt("deptNo"));
                        employee.setPassword(rs.getString("password"));
                        employee.setRealName(rs.getString("realName"));
                        employee.setSex(rs.getString("sex"));
                        employee.setBirthdate(rs.getDate("birthdate"));
                        employee.setHiredate(rs.getDate("hiredate"));
                        employee.setLeavedate(rs.getDate("leavedate"));
                        employee.setOnduty(rs.getInt("onduty"));
                        employee.setEmpType(rs.getInt("emptype"));
                        employee.setPhone(rs.getString("phone"));
                        employee.setQq(rs.getString("qq"));
                        employee.setEmerContactPerson("EMERCONTACTPERSON");
                        employee.setIdCard(rs.getString("idcard"));
                        empList.add(employee);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return empList;
            }
        };
        return DbUtils.queryGeneralCall(sql, handler);
    }

    @Override
    public int save(Employee emp) {
        String sql = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ? , ?, ?, ?, ?);";
        Object[] params = {
                emp.getEmpId(),emp.getMgrId(),emp.getPosId(),emp.getDeptNo(),
                emp.getPassword(),emp.getRealName(),emp.getSex(), emp.getBirthdate(),
                emp.getHiredate(),emp.getLeavedate(),emp.getOnduty(), emp.getEmpType(),
                emp.getPhone(),emp.getQq(),emp.getEmerContactPerson(), emp.getIdCard()
        };
        return DbUtils.update(sql, params);
    }

    @Override
    public List<Employee> findAll() {
        String sql = "select * from employee ";
        return DbUtils.queryGeneral(sql, Employee.class);
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Employee> list() {
        return list(null, null);
    }

    @Override
    public List<Employee> list(PageBean pageBean, Object[] params) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT * FROM employee e JOIN dept d ON e.DEPTNO = d.DEPTNO JOIN position p  ON p.POSID = e.POSID WHERE 1=1 ");
        if (params!=null && params.length==4){
            String empId = (String)params[0];
            int deptNo = (Integer)params[1];
            int onduty = (Integer)params[2];
            String hiredate = (String)params[3];
            if (!"".equals(empId)){
                sqlBuilder.append(" and empId like "+empId+"  ");
            }
            if (!"".equals(deptNo)&&deptNo!=0){
                sqlBuilder.append(" and deptNo="+deptNo+" ");
            }
            if (onduty!=0){
                sqlBuilder.append(" and onduty = "+onduty+" ");
            }
            if (!"".equals(hiredate)){
                sqlBuilder.append(" and hiredate = '"+hiredate+"' ");
            }
        }
        sqlBuilder.append(" ORDER BY empid desc  ");
        Object[] param = {pageBean.getStartRow(),pageBean.getSize()};
        if (pageBean!=null){
            sqlBuilder.append("  limit ?,? ");
        }else {
            // 如果不需要分页，则不传入参数
            param = null;
        }
        String sql = sqlBuilder.toString();
        System.out.println(sql);
        BeanListHandler handler = new BeanListHandler() {
            @Override
            public List resultHandler(ResultSet rs) {
                List<Employee> empList = new ArrayList<>();
                while (true) {
                    try {
                        if (!rs.next()) {
                            break;
                        }
                        Employee emp = new Employee();
                        emp.setEmpId(rs.getString("empId"));
                        emp.setRealName(rs.getString("realName"));
                        Department dept = new Department();
                        dept.setDeptNo(rs.getInt("deptNo"));
                        dept.setDeptName(rs.getString("deptName"));
                        emp.setDept(dept);
                        Position pos = new Position();
                        pos.setPosId(rs.getInt("posId"));
                        pos.setPname(rs.getString("pdesc"));
                        emp.setPos(pos);
                        emp.setHiredate(rs.getDate("hiredate"));
                        emp.setPhone(rs.getString("phone"));
                        emp.setOnduty(rs.getInt("onduty"));

                        empList.add(emp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return empList;
            }
        };
        List<Employee> empList = DbUtils.queryGeneralCall(sql, handler,param);
        pageBean.setList(empList);
        return empList;
    }

    @Override
    public int getTotal() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM employee";
        Connection conn = DbUtils.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                total = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return total;
    }

    /**
     * 根据员工编号查询员工信息
     * @param empId
     * @return
     */
    @Override
    public Employee findById(String empId) {

        String sql = "SELECT * FROM employee e JOIN dept d ON e.DEPTNO = d.DEPTNO JOIN position p  ON p.POSID = e.POSID  WHERE empId= '"+empId+"'";
        BeanHandler handler = new BeanHandler() {
            @Override
            public Employee resultHandler(ResultSet rs) {
                Employee emp = new Employee();
                while (true) {
                    try {
                        if (!rs.next()) {
                            break;
                        }
                        emp.setEmpId(rs.getString("empId"));
                        emp.setRealName(rs.getString("realName"));
                        Department dept = new Department();
                        dept.setDeptNo(rs.getInt("deptNo"));
                        dept.setDeptName(rs.getString("deptName"));
                        emp.setDept(dept);
                        emp.setDeptNo(dept.getDeptNo());
                        Position pos = new Position();
                        pos.setPosId(rs.getInt("posId"));
                        pos.setPname(rs.getString("pdesc"));
                        emp.setPos(pos);
                        emp.setPosId(pos.getPosId());
                        emp.setHiredate(rs.getDate("hiredate"));
                        emp.setPhone(rs.getString("phone"));
                        emp.setOnduty(rs.getInt("onduty"));
                        emp.setMgrId(rs.getString("emp_empId"));
                        emp.setPassword(rs.getString("password"));
                        emp.setEmpType(rs.getInt("empType"));
                        emp.setQq(rs.getString("QQ"));
                        emp.setEmerContactPerson(rs.getString("EMERCONTACTPERSON"));
                        emp.setSex(rs.getString("sex"));
                        emp.setBirthdate(rs.getDate("BIRTHDATE"));
                        emp.setLeavedate(rs.getDate("LEAVEDATE"));
                        emp.setIdCard(rs.getString("IDCARD"));

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return emp;
            }
        };
        Employee emp = DbUtils.queryOneGeneralCall(sql, handler);
        return emp;
    }

    /**
     * 修改员工信息
     * @param emp
     * @return
     */
    @Override
    public int update(Employee emp) {

        StringBuilder sqlBulider = new StringBuilder();
        sqlBulider.append("update employee ");
        boolean isFirst = true;
        if (emp.getMgrId()!=null&&!"".equals(emp.getMgrId())) {
            isFirst = false;
            sqlBulider.append(" set emp_empID='" + emp.getMgrId() + "' ");
        }
        if (emp.getPosId()!=0) {
            if (isFirst){
                sqlBulider.append(" set ");
                isFirst = false;
            }else {
                sqlBulider.append(" , ");
            }
            sqlBulider.append(" POSID=" + emp.getPosId());
        }
        if (emp.getDeptNo()!=0) {
            if (isFirst){
                sqlBulider.append(" set ");
                isFirst = false;
            }else {
                sqlBulider.append(" , ");
            }
            sqlBulider.append("  DEPTNO=" + emp.getDeptNo());
        }
        if (emp.getPassword()!=null&& !"".equals(emp.getPassword())) {
            if (isFirst){
                sqlBulider.append(" set ");
                isFirst = false;
            }else {
                sqlBulider.append(" , ");
            }
            sqlBulider.append("  PASSWORD= '" + emp.getPassword() + "' ");
        }
        if (emp.getRealName()!=null&&!"".equals(emp.getRealName())) {
            if (isFirst){
                sqlBulider.append(" set ");
                isFirst = false;
            }else {
                sqlBulider.append(" , ");
            }
            sqlBulider.append(" REALNAME= '" + emp.getRealName() + "' ");
        }
        if (emp.getSex()!=null && !"".equals(emp.getSex())) {
            if (isFirst){
                sqlBulider.append(" set ");
                isFirst = false;
            }else {
                sqlBulider.append(" , ");
            }
            sqlBulider.append("  SEX= '" + emp.getSex() + "'");
        }
        if (emp.getBirthdate()!=null) {
            if (isFirst){
                sqlBulider.append(" set ");
                isFirst = false;
            }else {
                sqlBulider.append(" , ");
            }
            sqlBulider.append("  BIRTHDATE='" + emp.getBirthdate() + "' ");
        }
        if (emp.getHiredate()!=null) {
            if (isFirst){
                sqlBulider.append(" set ");
                isFirst = false;
            }else {
                sqlBulider.append(" , ");
            }
            sqlBulider.append("  HIREDATE=' " + emp.getHiredate() + "'");
        }
        if (emp.getLeavedate()!=null) {
            if (isFirst){
                sqlBulider.append(" set ");
                isFirst = false;
            }else {
                sqlBulider.append(" , ");
            }
            sqlBulider.append("  LEAVEDATE= '" + emp.getLeavedate() + "'");
        }
        if (emp.getOnduty()!=0) {
            if (isFirst){
                sqlBulider.append(" set ");
                isFirst = false;
            }else {
                sqlBulider.append(" , ");
            }
            sqlBulider.append("  ONDUTY=" + emp.getOnduty());
        }
        if (emp.getQq()!=null&& !"".equals(emp.getQq())) {
            if (isFirst){
                sqlBulider.append(" set ");
                isFirst = false;
            }else {
                sqlBulider.append(" , ");
            }
            sqlBulider.append("  QQ='" + emp.getQq() + "'");
        }
        if (emp.getEmpType()!=0) {
            if (isFirst){
                sqlBulider.append(" set ");
                isFirst = false;
            }else {
                sqlBulider.append(" , ");
            }
            sqlBulider.append("  empType=" + emp.getEmpType());
        }
        if (emp.getPhone()!=null&&!"".equals(emp.getPhone())) {
            if (isFirst){
                sqlBulider.append(" set ");
                isFirst = false;
            }else {
                sqlBulider.append(" , ");
            }
            sqlBulider.append("  PHONE='" + emp.getPhone() + "'");
        }
        if (emp.getEmerContactPerson()!=null && !"".equals(emp.getEmerContactPerson())) {
            if (isFirst){
                sqlBulider.append(" set ");
                isFirst = false;
            }else {
                sqlBulider.append(" , ");
            }
            sqlBulider.append("  EMERCONTACTPERSON='" + emp.getEmerContactPerson() + "'");
        }
        if (emp.getIdCard()!=null && !"".equals(emp.getIdCard())) {
            if (isFirst){
                sqlBulider.append(" set ");
                isFirst = false;
            }else {
                sqlBulider.append(" , ");
            }
            sqlBulider.append("  IDCARD='" + emp.getIdCard() + "'");
        }

        sqlBulider.append(" where EMPID= '"+emp.getEmpId()+" '");
        String sql = sqlBulider.toString();
        int n = DbUtils.update(sql);
        return n;
    }
}
