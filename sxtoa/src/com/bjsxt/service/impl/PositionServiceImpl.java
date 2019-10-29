package com.bjsxt.service.impl;

import com.bjsxt.dao.PositionDao;
import com.bjsxt.dao.impl.PositionDaoImpl;
import com.bjsxt.entity.Position;
import com.bjsxt.service.PositionService;

import java.util.List;

public class PositionServiceImpl implements PositionService {
    private PositionDao posDao = new PositionDaoImpl();
    @Override
    public List<Position> findAll() {
        return posDao.findAll();
    }

    @Override
    public int add(Position pos) {
        return posDao.save(pos);
    }

    /*@Override
    public List<Position> showAll() {
        return posDao.showAll();
    }*/

    @Override
    public int delete(int posId) {
        return posDao.delete(posId);
    }

    @Override
    public Position findById(int posId) {
        return posDao.findById(posId);
    }

    @Override
    public int update(Position posId) {
        return posDao.update(posId);
    }
}
