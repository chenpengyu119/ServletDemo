package com.bjsxt.job.service.impl;

import com.bjsxt.job.dao.impl.TPositionDaoImpl;
import com.bjsxt.job.service.TPositionService;

import java.sql.Date;

/**
 * @author pengyu
 */
public class TPositionServiceImpl implements TPositionService {
    @Override
    public int publish(String pname, double minsal, double maxsal, Date releasedate, String cname) {
        return new TPositionDaoImpl().add(pname, minsal, maxsal, releasedate, cname);
    }

    @Override
    public int del(String pname) {

        return new TPositionDaoImpl().del(pname);
    }

}
