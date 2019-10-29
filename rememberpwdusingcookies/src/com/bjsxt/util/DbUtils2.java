package com.bjsxt.util;

import java.sql.*;

/**
 * 对增删改进行封装
 * @author pengyu
 */
public class DbUtils2 {

    private static final String DRIVER_NAME1 = "com.mysql.jdbc.Driver";
    private static final String URL1 = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8";
    private static final String USER_NAME1 = "root";
    private static final String PWD1 = "";


    public static Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL1, USER_NAME1, PWD1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 增删改
     * @param sql  sql命令
     * @param objs  sql参数
     * @return 执行结果
     */
    public static int update(String sql,Object ...objs) {

        // 创建对象
        Connection conn = null;
        PreparedStatement ps = null;
        int reslut = 0;
        try {
            conn = getConn();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            // 设置参数
            int i = 1;
            for (Object obj : objs) {
                ps.setObject(i++, obj);
            }
            System.out.println("执行SQL："+ps.toString());
            try {
                // 执行修改
                reslut = ps.executeUpdate();
                conn.commit();
            } catch (Exception e) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 关闭连接
            close(null, ps, conn);
        }

        return reslut;
    }

    /**
     *  查询
     */
    public static void query() {

        // 获取连接
        Connection conn = getConn();
        // 数据库对象
        String sql;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            sql = "select * from account";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("accname")+":"+rs.getInt("balance"));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs, ps, conn);
        }
    }


    /**
     * 关闭连接
     */
    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {

        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



}
