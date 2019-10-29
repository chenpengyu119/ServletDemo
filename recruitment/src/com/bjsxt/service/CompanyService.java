package com.bjsxt.service;

import com.bjsxt.entity.Company;

import java.util.List;

public interface CompanyService {
    /**
     * 查询所有公司信息
     * @return
     */
    public List<Company> findList();
}
