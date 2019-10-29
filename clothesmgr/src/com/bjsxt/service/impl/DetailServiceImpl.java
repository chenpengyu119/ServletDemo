package com.bjsxt.service.impl;

import com.bjsxt.dao.ClothesDao;
import com.bjsxt.dao.DetailDao;
import com.bjsxt.dao.impl.ClothesDaoImpl;
import com.bjsxt.dao.impl.DetailDaoImpl;
import com.bjsxt.entity.Detail;
import com.bjsxt.service.DetailService;
import com.bjsxt.util.DBUtil2;

import java.sql.Connection;
import java.sql.SQLException;

public class DetailServiceImpl implements DetailService {
    private DetailDao detailDao = new DetailDaoImpl();
    private ClothesDao clothesDao = new ClothesDaoImpl();
    @Override
    public Detail findByCid(int cid) {
        return detailDao.findByCid(cid);
    }

    @Override
    public void add(Detail detail) {

        Connection conn = DBUtil2.getConnection();
        try {
            conn.setAutoCommit(false);
            int cid = clothesDao.add(detail.getCloth());
            detail.setCid(cid);
            detailDao.add(detail);
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException(e1.getMessage());
            }
            throw new RuntimeException(e.getMessage());
        }finally {
           DBUtil2.closeAll(null,null,conn);
        }
    }
}
