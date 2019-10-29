package com.bjsxt.dao.impl;

import com.bjsxt.dao.PositionDao;
import com.bjsxt.entity.Position;
import com.bjsxt.util.DBUtil;
import com.bjsxt.util.DbUtils;

import java.util.List;

/**
 * @author pengyu
 */
public class PositionDaoImpl implements PositionDao {
    @Override
    public List<Position> findAll() {
        String sql = "select * from position";
        return DbUtils.queryGeneral(sql, Position.class);
    }

    @Override
    public int save(Position pos) {
        String sql = "insert into position values(?,?,?)";
        return DBUtil.executeUpdate(sql, pos.getPosId(),pos.getPname(),pos.getPdesc());
    }

    /*@Override
    public List<Position> showAll() {
        String sql = "select * from position ";
        return DbUtils.queryGeneral(sql, Position.class);
    }*/

    @Override
    public int delete(int posId) {
        String sql = "delete from position where posId = ? ";
        return DBUtil.executeUpdate(sql, posId);
    }

    @Override
    public Position findById(int posId) {
        String sql = "select * from position where posId="+posId;
        return DbUtils.queryOneGeneral(sql,Position.class);
    }

    @Override
    public int update(Position pos) {
        String sql = "update position set pname = ? , pdesc = ? where posId = ?";
        Object[] params = {pos.getPname(),pos.getPdesc(),pos.getPosId()};
        return DBUtil.executeUpdate(sql, params);
    }
}
