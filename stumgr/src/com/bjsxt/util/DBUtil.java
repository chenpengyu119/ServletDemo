package com.bjsxt.util;

import java.sql.*;

/**
 * JDBC的公共类
 * 
 * @author Administrator
 * 
 */
public class DBUtil {


	/**
	 * 获取数据库连接
	 * @return
	 */
	public static  Connection getConnection(){
		String driver = "com.mysql.jdbc.Driver";
		// ip  port  dbname
		String url = "jdbc:mysql://127.0.0.1:3306/stumgr?useUnicode=true&characterEncoding=utf-8";
		String user = "root";
		String password = "";
		Connection conn = null;
		try {
			//加载驱动
			Class.forName(driver);
			//建立和数据库的连接
			conn = DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	/**
	 * 关闭数据库资源
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void closeAll(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 执行insert、update、delete 三个DML操作
	 * @param sql
	 * @param prams
	 * @return
	 * @throws SQLException 
	 */
	public static int executeUpdate(String sql, Object[] prams) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = getConnection();
		int n = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < prams.length; i++) {
				pstmt.setObject(i + 1, prams[i]);
			}
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			//在该层次处理异常
			e.printStackTrace();		
			//如果需要，可以再抛给上一层
			throw e;
		}
		return n;
	}
}
