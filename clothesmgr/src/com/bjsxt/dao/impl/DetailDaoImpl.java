package com.bjsxt.dao.impl;

import com.bjsxt.dao.DetailDao;
import com.bjsxt.entity.Detail;
import com.bjsxt.util.DBUtil;
import com.bjsxt.util.DBUtil2;
import sun.security.pkcs11.Secmod;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetailDaoImpl implements DetailDao {
    @Override
    public Detail findByCid(int cid) {

        Connection conn = DBUtil.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Detail detail = new Detail();

        String sql = "select * from detail  where c_id = "+cid;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            detail.setDcolor(rs.getString("d_color"));
            detail.setDprice(rs.getFloat("d_price"));
            detail.setDsize(rs.getFloat("d_size"));

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(rs, stmt, conn);
        }


        return detail;
    }

    @Override
    public void add(Detail detail) {

        String sql = "insert into detail values(null,?,?,?,?)";
        Object[] params = {
                detail.getDcolor(),
                detail.getDprice(),
                detail.getDsize(),
                detail.getCid()
        };
        DBUtil2.executeUpdate(sql, params);
    }
}
