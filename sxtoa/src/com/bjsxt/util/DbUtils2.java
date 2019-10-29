package com.bjsxt.util;

import com.bjsxt.entity.Expense;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 对增删改进行封装
 *
 * @author pengyu
 */
public class DbUtils2 {

    /**
     * 用来存放数据库连接
     */
    static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/sxtoa?useUnicode=true&characterEncoding=gb2312";
    private static final String USER_NAME = "root";
    private static final String PWD = "";

    public static Connection getConn() {

        Connection conn  = threadLocal.get();
        if (conn==null) {
            try {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(URL, USER_NAME, PWD);
                threadLocal.set(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 增删改
     *
     * @param sql  sql命令
     * @param objs sql参数
     * @return 执行结果
     */
    public static int update(String sql, Object... objs) {

        // 创建对象
        Connection conn = null;
        PreparedStatement ps = null;
        int reslut = 0;
        try {
            conn = getConn();
            ps = conn.prepareStatement(sql);
            // 设置参数
            int i = 1;
            for (Object obj : objs) {
                ps.setObject(i++, obj);
            }
            System.out.println("执行SQL：" + ps.toString());
            try {
                // 执行修改
                reslut = ps.executeUpdate();
            } catch (Exception e) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            close(null, ps, null);
        }

        return reslut;
    }

    /**
     * 查询
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
                System.out.println(rs.getString("accname") + ":" + rs.getInt("balance"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, null);
        }
    }

    public static <T> List<T> queryGeneral(String sql, Class<T> clazz) {

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
                    String methodName = "set" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
                    System.out.println(methodName);
                    Method setMethod = null;
                    try {
                        setMethod = clazz.getDeclaredMethod(methodName, field.getType());
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }

                    try {
                        if (rs.getObject(name) != null) {
                            setMethod.invoke(t, rs.getObject(name));
                        }
                    }catch (SQLException e){
                        System.out.println(t.getClass());
                        try {
                            setMethod.invoke(t, rs.getObject("emp_empId"));
                        }catch (IllegalArgumentException e2){
                            e2.printStackTrace();
                        }
                    }
                }
                list.add(t);
            }
        } catch (NullPointerException e) {
            System.out.println("空指针");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, null);
        }
        return list;
    }

    /**
     * 单条查询
     * @param sql
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T>  T queryOneGeneral(String sql,Class<T> clazz){

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
                    Method setMethod = null;
                    try {
                        setMethod = clazz.getDeclaredMethod(methodName,field.getType());
                    }catch (NoSuchMethodException e){
                        System.out.println("没有找到方法"+methodName);
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
            close(rs, ps, null);
        }
        return t;
    }

    /**
     * 使用接口回调
     * 复数件
     * @param sql
     * @param <T>
     * @return
     */
    public static <T> List<T> queryGeneralCall(String sql , BeanListHandler handler , Object... objs) {

        // 获得连接
        Connection conn = getConn();
        // 创建对象
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);

            int i=1;
            if (objs!=null && objs.length!= 0){
                for (Object obj:objs) {
                    ps.setObject(i++, obj);
                }
            }
            rs = ps.executeQuery();
            // 调用回调函数
            list = handler.resultHandler(rs);
        } catch (NullPointerException e) {
            System.out.println("空指针");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, null);
        }
        return list;
    }

    /**
     * 使用接口回调
     * 单件
     * @param sql
     * @param <T>
     * @return
     */
    public static <T> T queryOneGeneralCall(String sql , BeanHandler handler , Object... objs) {

        // 获得连接
        Connection conn = getConn();
        // 创建对象
        PreparedStatement ps = null;
        ResultSet rs = null;
        Object object = null;
        try {
            ps = conn.prepareStatement(sql);
            System.out.println("查询语句："+sql);

            int i=1;
            if (objs!=null && objs.length!= 0){
                for (Object obj:objs) {
                    ps.setObject(i++, obj);
                    System.out.print("查询参数："+obj);
                }
            }
            rs = ps.executeQuery();
            // 调用回调函数
            object = handler.resultHandler(rs);
        } catch (NullPointerException e) {
            System.out.println("空指针");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, null);
        }
        return (T)object;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
