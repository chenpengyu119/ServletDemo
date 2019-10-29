package com.bjsxt.job.test;

import com.bjsxt.job.dao.JobDao;
import com.bjsxt.job.dao.impl.JobDaoImpl;

public class TestJob {
    public static void main(String[] args) {
        JobDao dao = new JobDaoImpl();
        dao.find("老");
    }
}
