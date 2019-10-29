package com.bjsxt.dao.impl;

import com.bjsxt.dao.AuditingDao;
import com.bjsxt.entity.Auditing;
import com.bjsxt.util.DbUtils2;

import java.sql.Connection;

public class AuditingDaoImpl implements AuditingDao {


    @Override
    public void add(Auditing auditing) {
        if (auditing!=null) {
            String sql = "insert into auditing values(null,?,?,?,?,?)";
            Object[] params = {
                    auditing.getExpId(),
                    auditing.getEmpId(),
                    auditing.getResult(),
                    auditing.getAuditDesc(),
                    auditing.getTime()
            };
            DbUtils2.update(sql, params);
        }
    }
}
