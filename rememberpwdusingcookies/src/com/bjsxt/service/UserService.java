package com.bjsxt.service;

import com.bjsxt.entity.User;

import java.util.List;

public interface UserService {
    public User login(String userId, String password);
    public boolean register(User user);
    public List<User> findAll();
    public List<User> find(String userId,int age);
}
