package com.bjsxt.dao.impl;

import com.bjsxt.dao.ClothesDao;
import com.bjsxt.entity.Cloth;
import com.bjsxt.util.DBUtil;
import com.bjsxt.util.DBUtil2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClothesDaoImpl implements ClothesDao {
    @Override
    public List<Cloth> findByName(String cname) {

        Connection conn = DBUtil.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        List<Cloth> clothList = new ArrayList<>();
        String sql = "select * from cloth ";
        if (cname!=null&&!"".equals(cname)){
            cname = cname.trim();
            sql+=" where c_name like '%"+cname+"%'";
        }

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                Cloth cloth = new Cloth();
                cloth.setCid(rs.getInt("c_id"));
                cloth.setCname(rs.getString("c_name"));
                cloth.setCgender(rs.getString("c_gender"));
                cloth.setEbrand(rs.getString("e_brand"));
                clothList.add(cloth);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(rs, stmt, conn);
        }

        return clothList;
    }

    @Override
    public int add(Cloth cloth) {

        String sql = "insert into cloth values(null,?,?,?)";
        PreparedStatement ps = null;
        Connection conn = DBUtil2.getConnection();
        ResultSet rs =null;
        int cid = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, cloth.getCname());
            ps.setString(2, cloth.getEbrand());
            ps.setString(3, cloth.getCgender());

            ps.executeUpdate();

            String sql2 = "select @@identity";
            ps = conn.prepareStatement(sql2);
            rs = ps.executeQuery();
            rs.next();
            cid = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            DBUtil2.closeAll(rs, null, null);
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }


        return cid;
    }
}
