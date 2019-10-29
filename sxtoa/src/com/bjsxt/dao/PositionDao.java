package com.bjsxt.dao;

import com.bjsxt.entity.Position;

import java.util.List;

public interface PositionDao {
    public List<Position> findAll();
    public int save(Position pos);
    //public List<Position> showAll();
    public int delete(int posId);
    public Position findById(int posId);
    public int update(Position pos);
}
