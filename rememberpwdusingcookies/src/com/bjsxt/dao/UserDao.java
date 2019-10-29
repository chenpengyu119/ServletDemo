package com.bjsxt.dao;

import com.bjsxt.entity.User;

import java.util.List;

public interface UserDao {

    public User find(String userId);

    public int add(User user);

    public List<User> findAll();

    public List<User> find(String userId,int age);
}
