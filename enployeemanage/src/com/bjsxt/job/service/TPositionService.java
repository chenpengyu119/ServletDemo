package com.bjsxt.job.service;

import java.sql.Date;

public interface TPositionService {
    public int publish(String pname, double minsal, double maxsal, Date releasedate, String cname);
    public int del(String pname);
}
