package com.bjsxt.job.service;

import com.bjsxt.job.entity.Job;

import java.util.List;

public interface JobService {
    public List<Job> find(String pname);
}
