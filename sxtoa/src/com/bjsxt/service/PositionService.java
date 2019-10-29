package com.bjsxt.service;

import com.bjsxt.entity.Position;


import java.util.List;

/**
 * @author pengyu
 */
public interface PositionService {

    public List<Position> findAll();

    /**
     * 添加一个岗位
     * @param pos
     * @return
     */
    public int add(Position pos);

    //public List<Position> showAll();

    public int delete(int posId);

    public Position findById(int posId);

    public int update(Position posId);
}
