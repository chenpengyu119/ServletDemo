package com.bjsxt.dao.impl;

import com.bjsxt.dao.UserDao;
import com.bjsxt.entity.User;
import com.bjsxt.util.DBUtil;
import com.bjsxt.util.DbUtils2;
import com.bjsxt.util.DbUtils3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User find(String userId) {
        //0.将相应数据库的jar包放入项目
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        try{
            //2.建立（和数据库）连接
            conn = DBUtil.getConnection();
            //3.创建一个SQL命令发送器
            String sql = "select * from user where userid = ?";
            pstmt = conn.prepareStatement(sql);
            //4.使用SQL命令发送器（手枪）来发送SQL命令（子弹）并得到结果
            pstmt.setString(1,userId);
            rs = pstmt.executeQuery();
            //5.处理结果
            if(rs.next()){
                //获取当前行各个列的数据
                String realName = rs.getString("realName");
                int age = rs.getInt("age");
                String hobby = rs.getString("hobby");
                Date enterDate = rs.getDate("enterDate");
                String password = rs.getString("password");
                user = new User(userId,realName,password,age,hobby,enterDate);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }  finally{
            //6.关闭各种数据库资源
            DBUtil.closeAll(rs,pstmt,conn);
        }
        return user;
    }

    @Override
    public int add(User user) {
        String sql = "insert into user values(?,?,?,?,?,?)";

        int res = DbUtils2.update(sql, user.getUserId(),user.getRealname(),user.getPassword(),user.getAge(),user.getHobby()
        ,user.getEnterDate());
        return res;
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        Class clazz = null;
        try {
            clazz = Class.forName("com.bjsxt.entity.User");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<User> list = DbUtils3.queryGeneral(sql, clazz);

        for (User entity:list) {
            System.out.println(entity);
        }

        return list;
    }

    @Override
    public List<User> find(String userId,int age) {

        StringBuilder sql = new StringBuilder("select * from user where 1=1  ");
        if (userId !="" ){
            sql.append(" and userId like '%"+userId+"%' ");
        }
        if (age != 0){
            sql.append(" and age="+age+" ");
        }
        Class clazz = null;
        try {
            clazz = Class.forName("com.bjsxt.entity.User");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<User> userList = DbUtils3.queryGeneral(sql.toString(), clazz);
        return userList;
    }


}
