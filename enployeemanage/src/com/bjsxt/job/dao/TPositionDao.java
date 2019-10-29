package com.bjsxt.job.dao;

import com.bjsxt.job.entity.Job;
import com.bjsxt.job.entity.TCompany;
import com.bjsxt.job.entity.TPosition;

import java.awt.*;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface TPositionDao {
    public int add(String pname, double minsal, double maxsal, Date releasedate, String cname);
    public int del(String pname);
}
