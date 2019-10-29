package com.bjsxt.job.dao;

import com.bjsxt.job.entity.Job;

import java.util.List;

public interface JobDao {
    /**
     * 根据职位名称查询职位信息
     */
    public List<Job> find(String pname);
}
