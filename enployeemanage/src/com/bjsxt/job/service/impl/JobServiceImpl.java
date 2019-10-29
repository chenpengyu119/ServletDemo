package com.bjsxt.job.service.impl;

import com.bjsxt.job.dao.impl.JobDaoImpl;
import com.bjsxt.job.entity.Job;
import com.bjsxt.job.service.JobService;

import java.util.List;

public class JobServiceImpl implements JobService {
    @Override
    public List<Job> find(String pname) {
        return new JobDaoImpl().find(pname);
    }
}
