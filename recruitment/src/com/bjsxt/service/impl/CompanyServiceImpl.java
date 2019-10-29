package com.bjsxt.service.impl;

import com.bjsxt.dao.CompanyDao;
import com.bjsxt.dao.impl.CompanyDaoImpl;
import com.bjsxt.entity.Company;
import com.bjsxt.service.CompanyService;

import java.util.List;

public class CompanyServiceImpl implements CompanyService {
    private CompanyDao companyDao = new CompanyDaoImpl();
    @Override
    public List<Company> findList() {
        return companyDao.findList();
    }
}
