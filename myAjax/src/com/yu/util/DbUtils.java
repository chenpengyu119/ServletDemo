package com.yu.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 对增删改进行封装
 * @author pengyu
 */
public class DbUtils {

    private static final String DRIVER_NAME1 = "com.mysql.jdbc.Driver";
    private static final String URL1 = "jdbc:mysql://127.0.0.1:3306/testajax?useUnicode=true&characterEncoding=utf8";
    private static final String USER_NAME1 = "root";
    private static final String PWD1 = "";

    public static Connection getConn() {

        Connection conn = null;
        try {
            System.out.println("pwd"+PWD1);
            System.out.println("url"+URL1);
            System.out.println("username"+USER_NAME1);
            Class.forName(DRIVER_NAME1);
            conn = DriverManager.getConnection(URL1, USER_NAME1, PWD1);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e2){
            e2.printStackTrace();
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

    public static <T>  List<T> queryGeneral(String sql,Class<T> clazz){

        // 加载驱动
        try {
            Class.forName(DRIVER_NAME1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 获得连接
        Connection conn = getConn();
        // 创建对象
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            Field[] fields = clazz.getDeclaredFields();

            while (rs.next()) {
                // 给该类的所有属性赋值
                T t = clazz.getConstructor().newInstance();
                for (Field field : fields) {
                    String name = field.getName();
                        String methodName = "set"+Character.toUpperCase(name.charAt(0))+name.substring(1);
                        System.out.println(methodName);
                        Method setMethod;
                        try {
                        setMethod = clazz.getDeclaredMethod(methodName,field.getType());
                    }catch (NoSuchMethodException e){
                        setMethod = clazz.getSuperclass().getDeclaredMethod(methodName,field.getType());
                    }


                    if (rs.getObject(name) != null) {
                        System.out.println(rs.getObject(name));
                        setMethod.invoke(t, rs.getObject(name));
                    }
                }
                list.add(t);
            }
        }catch (NullPointerException e){
            System.out.println("空指针");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(rs, ps, conn);
        }
        return list;
    }


    public static <T>  T queryOneGeneral(String sql,Class<T> clazz){
        // 加载驱动
        try {
            Class.forName(DRIVER_NAME1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 获得连接
        Connection conn = getConn();
        // 创建对象
        PreparedStatement ps = null;
        ResultSet rs = null;
        T t = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            Field[] fields = clazz.getDeclaredFields();

            t = clazz.getConstructor().newInstance();
            while (rs.next()) {
                // 给该类的所有属性赋值
                for (Field field : fields) {
                    String name = field.getName();
                    String methodName = "set"+Character.toUpperCase(name.charAt(0))+name.substring(1);
                    System.out.println(methodName);
                    Method setMethod;
                    try {
                        setMethod = clazz.getDeclaredMethod(methodName,field.getType());
                    }catch (NoSuchMethodException e){
                        setMethod = clazz.getSuperclass().getDeclaredMethod(methodName,field.getType());
                    }


                    if (rs.getObject(name) != null) {
                        System.out.println(rs.getObject(name));
                        setMethod.invoke(t, rs.getObject(name));
                    }
                }
            }
        }catch (NullPointerException e){
            System.out.println("空指针");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(rs, ps, conn);
        }
        return t;
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
