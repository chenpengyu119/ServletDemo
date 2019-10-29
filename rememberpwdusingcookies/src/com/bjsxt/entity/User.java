package com.bjsxt.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {


   // private static final long serialVersionUID  = 111;
    private String userId;
    private String realname;
    private String password;
    private int age;
    private String hobby;
    private Date enterDate;

    public User() {
    }

    public User(String userId, String realname, String password, int age, String hobby, Date enterDate) {
        this.userId = userId;
        this.realname = realname;
        this.password = password;
        this.age = age;
        this.hobby = hobby;
        this.enterDate = enterDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", realname='" + realname + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                ", enterDate=" + enterDate +
                '}';
    }
}
