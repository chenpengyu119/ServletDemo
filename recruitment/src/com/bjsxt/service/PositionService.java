package com.bjsxt.service;

import com.bjsxt.entity.Position;

import java.util.List;

public interface PositionService {
    /**
     * 根据条件查询职位信息
     * @param pname
     */
    public List<Position> findByPname(String pname);

    public int add(Position pos);

    public int del(int pid);
}
