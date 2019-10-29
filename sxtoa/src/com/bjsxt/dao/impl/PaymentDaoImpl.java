package com.bjsxt.dao.impl;

import com.bjsxt.dao.PaymentDao;
import com.bjsxt.entity.Payment;
import com.bjsxt.util.DbUtils2;

public class PaymentDaoImpl implements PaymentDao {
    @Override
    public void add(Payment pm) {
        String sql = "insert into payment values(null,?,?,?,?,?)";
        Object[] params = {
                pm.getExpId(),
                pm.getPayEmpId(),
                pm.getAmount(),
                pm.getPayTime(),
                pm.getEmpId()
        };
        DbUtils2.update(sql, params);
    }
}
