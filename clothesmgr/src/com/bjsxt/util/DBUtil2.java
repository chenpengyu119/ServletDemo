package com.bjsxt.util;

import java.sql.*;


/**
 * 数据库工具类
 * @author Administrator
 *
 */
public class DBUtil2 {

	private static ThreadLocal threadLocal = new ThreadLocal();

	/**
	 * 建立数据库连接并返回
	 * @return
	 */
	public static Connection getConnection(){
		String className = "com.mysql.jdbc.Driver";
		String url="jdbc:mysql://127.0.0.1:3306/clothesmgr?useUnicode=true&characterEncoding=gb2312";
		String user = "root";
		String password="";
		Connection conn = null;

		conn = (Connection) threadLocal.get();
		if (conn==null) {
			try {
				//加载JDBC访问Oracle的驱动
				Class.forName(className);
				//建立和数据库的连接
				conn = DriverManager.getConnection(url, user, password);
				threadLocal.set(conn);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	/**
	 * 关闭数据库资源
	 */
	public static void closeAll(ResultSet rs ,Statement stmt,Connection conn){
		try {
			if(rs != null){
				rs.close();
			}				
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		
		try {
			if(stmt != null){
				stmt.close();
			}				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(conn != null){
				conn.close();
			}				
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}
	/**
	 * 执行DML操作：insert、update、delete
	 * @param sql
	 * @return
	 */
	public static int executeUpdate(String sql,Object [] params) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		int n = 0;
		try {			
			//建立和数据库的连接
			conn =  getConnection();
			
			//创建SQL命令发送器			
			pstmt = conn.prepareStatement(sql);
			
			//使用SQL命令发送器发送SQL命令并得到结果
			for(int i=0;i<params.length;i++){				
				pstmt.setObject(i+1, params[i]);
			}			
			n = pstmt.executeUpdate();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//关闭资源			
			closeAll(null, pstmt, null);
		}		
		//返回结果
		return n;
	}
	
}
