package com.bjsxt.job.dao.impl;

import com.bjsxt.job.dao.TCompanyDao;
import com.bjsxt.job.entity.TCompany;
import com.bjsxt.job.utils.DbUtils;

import java.util.List;

/**
 * @author pengyu
 */
public class TCompanyDaoImpl implements TCompanyDao {
    @Override
    public List<TCompany> findCompany() {

        String sql = "select * from t_company";
        Class clazz = null;
        try {
            clazz = Class.forName("com.bjsxt.job.entity.TCompany");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<TCompany> list = DbUtils.queryGeneral(sql, clazz);

        for (TCompany entity:list) {
            System.out.println(entity);
        }

        return list;
    }
}
