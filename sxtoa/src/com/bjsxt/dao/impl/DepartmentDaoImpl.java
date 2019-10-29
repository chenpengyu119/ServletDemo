package com.bjsxt.dao.impl;

import com.bjsxt.dao.DepartmentDao;
import com.bjsxt.entity.Department;
import com.bjsxt.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pengyu
 */
public class DepartmentDaoImpl implements DepartmentDao {
    @Override
    public int save(Department dept) {
        String sql = "insert into dept values(?,?,?)";
        return DBUtil.executeUpdate(sql, dept.getDeptNo(),dept.getDeptName(),dept.getLocation());
    }

    @Override
    public List<Department> showAll() {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Department > deptList = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            //创建SQL命令发送器（手枪）
            String sql = "select * from dept";
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()){
                Department dept = new Department();
                dept.setDeptNo(rs.getInt("deptNo"));
                dept.setDeptName(rs.getString("deptName"));
                dept.setLocation(rs.getString("location"));
                deptList.add(dept);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            //关闭资源
            DBUtil.closeAll(null,pstmt,conn);
        }
        return deptList;
    }

    @Override
    public int delete(int deptNo) {
        String sql = "delete from dept where deptNo = ? ";
        return DBUtil.executeUpdate(sql, deptNo);
    }

    @Override
    public Department findById(int deptNo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Department dept = new Department();
        try{
            conn = DBUtil.getConnection();
            //创建SQL命令发送器（手枪）
            String sql = "select * from dept where deptNo="+deptNo;
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            if (rs.next()){
                dept.setDeptNo(rs.getInt("deptNo"));
                dept.setDeptName(rs.getString("deptName"));
                dept.setLocation(rs.getString("location"));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            //关闭资源
            DBUtil.closeAll(null,pstmt,conn);
        }
        return dept;
    }

    @Override
    public int update(Department dept) {
        String sql = "update dept set deptName = ? , location = ? where deptNo = ?";
        Object[] params = {dept.getDeptName(),dept.getLocation(),dept.getDeptNo()};
        return DBUtil.executeUpdate(sql, params);
    }
}
