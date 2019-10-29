package com.bjsxt.util;

import java.sql.*;

public class DBUtil {
    private DBUtil(){

    }

    /**
     * 获取数据库连接
     * @return
     */
    public static  Connection getConnection(){
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/sxtoa?useUnicode=true&characterEncoding=gb2312";
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

    public static void closeAll(ResultSet rs, Statement stmt, Connection conn){
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
     * 执行DML操作 insert update delete
     * @param sql
     * @param params
     * @return
     */
    public static int executeUpdate(String sql ,Object ... params) {//Object [] params
        Connection conn = null;
        PreparedStatement pstmt = null;
        int n =0;
        try{
            conn = DBUtil.getConnection();
            //创建SQL命令发送器（手枪）

            pstmt = conn.prepareStatement(sql);

            //使用SQL命令发送器发送SQL命令给数据库并得到结果（子弹）
            for(int i=0;i<params.length;i++){
                pstmt.setObject(i+1,params[i]);
            }

            n = pstmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            //关闭资源
            DBUtil.closeAll(null,pstmt,conn);
        }
        //返回数据
        return n;
    }
}
