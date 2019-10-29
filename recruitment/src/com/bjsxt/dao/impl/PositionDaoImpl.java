package com.bjsxt.dao.impl;

import com.bjsxt.dao.PositionDao;
import com.bjsxt.entity.Company;
import com.bjsxt.entity.Position;
import com.bjsxt.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionDaoImpl implements PositionDao {
    @Override
    public List<Position> findByPname(String pname) {

        Connection conn = DBUtil.getConnection();
        Statement ps = null;
        ResultSet rs = null;
        List<Position> posList = new ArrayList<>();

        StringBuilder sql = new StringBuilder("select * from t_position pos join t_company comp on pos.cid =  comp.cid ");
        if (pname!=null&&!"".equals(pname)){
            pname = pname.trim();
          sql.append(" where pname like '%"+pname+"%'");
        }

        try {
            ps = conn.createStatement();
            rs = ps.executeQuery(sql.toString());
            while (rs.next()){
                Position pos = new Position();
                pos.setPid(rs.getInt("pid"));
                pos.setPname(rs.getString("pname"));
                pos.setMaxsal(rs.getDouble("maxsal"));
                pos.setMinsal(rs.getDouble("minsal"));
                pos.setReleasedate(rs.getDate("releasedate"));
                Company company = new Company();
                company.setCname(rs.getString("cname"));
                company.setCid(rs.getInt("cid"));
                company.setCname(rs.getString("cname"));
                company.setLocation(rs.getString("location"));
                pos.setCompany(company);
                posList.add(pos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(rs, ps, conn);
        }

        return posList;
    }

    @Override
    public int add(Position pos) {

        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        int n=0;

        String sql = "insert into t_position values(null,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, pos.getPname());
            ps.setDouble(2, pos.getMinsal());
            ps.setDouble(3, pos.getMaxsal());
            ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            ps.setInt(5, pos.getCid());

            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps!=null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBUtil.closeAll(null,null,conn);
        }
        return n;
    }

    @Override
    public int del(int pid) {

        String sql = "delete from t_position where pid = ?";
        Object[] params = {pid};
        return DBUtil.executeUpdate(sql,params);
    }
}
