package com.bjsxt.test;

import com.bjsxt.dao.UserDao;
import com.bjsxt.dao.impl.UserDaoImpl;
import com.bjsxt.entity.User;
import com.bjsxt.util.DBUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TestUserDao {
    public static void main(String[] args) {

        UserDao userDao = new UserDaoImpl();
        /*User user = userDao.find("zhangsan", "zhangsan");
        System.out.println(user);*/
        /*String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String enterDateStr = format.format(new Date());
        Date enterDate = null;
        try {
            enterDate =  format.parse(enterDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int res = userDao.add(new User("lisi", "李四", "lisi", 18, "music",enterDate));
        System.out.println("插入结果："+res);*/

        // 测试查询全部数据
        List<User> list = userDao.find("roo",12);
        for (User u:list){
            System.out.println(u);
        }

    }
}
