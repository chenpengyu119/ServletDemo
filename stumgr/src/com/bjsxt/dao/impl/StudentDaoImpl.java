package com.bjsxt.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.bjsxt.dao.StudentDao;
import com.bjsxt.entity.Student;
import com.bjsxt.util.DBUtil;
import com.bjsxt.util.PageBean;

public class StudentDaoImpl implements StudentDao {
	@Override
	public List<Student> findAll() {
		Connection conn =DBUtil.getConnection();
		Statement stmt =null;
		ResultSet rs =null;
		List <Student> stuList = new ArrayList<Student>();
		try {
			stmt =conn.createStatement();
			rs = stmt.executeQuery("select * from student");
			while(rs.next()){
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setAge(rs.getInt("age"));
				stu.setScore(rs.getDouble("score"));
				stuList.add(stu);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, stmt, conn);
		}
		return stuList;
	}

	@Override
	public List<Student> findPage(PageBean pageBean) {

		Connection conn =DBUtil.getConnection();
		Statement stmt =null;
		ResultSet rs =null;
		List <Student> stuList = new ArrayList<Student>();
		try {
			stmt =conn.createStatement();
			rs = stmt.executeQuery("select * from student limit "+pageBean.getStartRow()+","+pageBean.getSize());
			while(rs.next()){
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setName(rs.getString("name"));
				stu.setAge(rs.getInt("age"));
				stu.setScore(rs.getDouble("score"));
				stuList.add(stu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, stmt, conn);
		}
		return stuList;
	}

	/**
	 * 获取总记录数
	 * @return
	 */
	@Override
	public int findCount() {
		Connection conn =DBUtil.getConnection();
		Statement stmt =null;
		ResultSet rs =null;
		int n = 0;
		try {
			stmt =conn.createStatement();
			rs = stmt.executeQuery("select count(1) from student");
			rs.next();
			n = rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, stmt, conn);
		}
		return n;
	}

	@Override
	public int findCount(String name, double minScore) {
		Connection conn =DBUtil.getConnection();
		Statement stmt =null;
		ResultSet rs =null;
		int n = 0;
		try {
			stmt =conn.createStatement();
			StringBuilder sql = new StringBuilder("select count(1) from student where 1=1 ");
			if (name!=null&&!"".equals(name)){
				sql.append(" and name like '%"+name+"%' ");
			}
			if (minScore!=0){
				sql.append(" and score >= "+minScore);
			}
			rs = stmt.executeQuery(sql.toString());
			rs.next();
			n = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, stmt, conn);
		}
		return n;
	}

	@Override
	public List<Student> findPage(PageBean<Student> pageBean, String name, double minScore) {
        Connection conn =DBUtil.getConnection();
        Statement stmt =null;
        ResultSet rs =null;
        List <Student> stuList = new ArrayList<Student>();
        try {
            stmt =conn.createStatement();
            StringBuilder sql = new StringBuilder("select * from student where 1=1 ");
            if (name!=null&&!"".equals(name)){
                sql.append(" and name like '%"+name+"%' ");
            }
            if (minScore!=0){
                sql.append(" and score >= "+minScore);
            }
            sql.append("order by id limit "+pageBean.getStartRow()+","+pageBean.getSize());
            rs = stmt.executeQuery(sql.toString());
            while(rs.next()){
                Student stu = new Student();
                stu.setId(rs.getInt("id"));
                stu.setName(rs.getString("name"));
                stu.setAge(rs.getInt("age"));
                stu.setScore(rs.getDouble("score"));
                stuList.add(stu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.closeAll(rs, stmt, conn);
        }
        return stuList;
	}
}
