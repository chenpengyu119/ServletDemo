package com.bjsxt.dao;

import com.bjsxt.entity.Position;

import java.util.List;

public interface PositionDao {
    public List<Position> findByPname(String pname);

    public int add(Position pos);

    public int del(int pid);
}
