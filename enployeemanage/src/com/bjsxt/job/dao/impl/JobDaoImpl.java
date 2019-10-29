package com.bjsxt.job.dao.impl;

import com.bjsxt.job.dao.JobDao;
import com.bjsxt.job.entity.Job;
import com.bjsxt.job.utils.DbUtils;

import java.util.List;

public class JobDaoImpl implements JobDao {

    @Override
    public List<Job> find(String pname) {

        StringBuilder sql = new StringBuilder("SELECT c.cname,c.location as location,p.pid as pid,p.pname as pname,p.minsal as minsal,p.maxsal as maxsal, p.releasedate as releasedate,p.cid as cid FROM t_company as c,t_position as p WHERE p.cid=c.cid ");
        if (pname!=null && pname !="" ){
            sql.append(" and p.pname LIKE '%"+pname+"%' ");
        }

        Class clazz = null;
        try {
            clazz = Class.forName("com.bjsxt.job.entity.Job");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<Job> userList = DbUtils.queryGeneral(sql.toString(), clazz);

        for (Job j:userList){
            System.out.println(j);
        }
        return userList;
    }
}
