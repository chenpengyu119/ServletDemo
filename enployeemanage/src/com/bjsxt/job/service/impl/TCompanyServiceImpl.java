package com.bjsxt.job.service.impl;

import com.bjsxt.job.dao.impl.TCompanyDaoImpl;
import com.bjsxt.job.entity.TCompany;
import com.bjsxt.job.service.TCompanyService;

import java.sql.Date;
import java.util.List;

/**
 * @author pengyu
 */
public class TCompanyServiceImpl implements TCompanyService {

    @Override
    public List<TCompany> findCompany() {
        return new TCompanyDaoImpl().findCompany();
    }
}
