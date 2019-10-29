package com.bjsxt.service.impl;

import com.bjsxt.dao.PositionDao;
import com.bjsxt.dao.impl.PositionDaoImpl;
import com.bjsxt.entity.Position;
import com.bjsxt.service.PositionService;

import java.util.List;

public class PositionServiceImpl implements PositionService {
    private PositionDao positionDao = new PositionDaoImpl();
    @Override
    public List<Position> findByPname(String pname) {
        return positionDao.findByPname(pname);

    }

    @Override
    public int add(Position pos) {
        return positionDao.add(pos);
    }

    @Override
    public int del(int pid) {
        return positionDao.del(pid);
    }
}
