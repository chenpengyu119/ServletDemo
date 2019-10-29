package com.bjsxt.service.impl;

import com.bjsxt.dao.UserDao;
import com.bjsxt.dao.impl.UserDaoImpl;
import com.bjsxt.entity.User;
import com.bjsxt.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public User login(String userId, String password) {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.find(userId);
        return user;
    }

    @Override
    public boolean register(User user) {

        // 判断用户id是否存在
        UserDao userDao = new UserDaoImpl();
        User userRes = userDao.find(user.getUserId());
        boolean res = false;
        if (userRes == null){
            // 可以插入
            res = userDao.add(user)>0?true:false;
        }else {
            return false;
        }
        return res;
    }

    @Override
    public List<User> findAll() {
        return new UserDaoImpl().findAll();
    }

    @Override
    public List<User> find(String userId, int age) {
        return new UserDaoImpl().find(userId, age);
    }


}
