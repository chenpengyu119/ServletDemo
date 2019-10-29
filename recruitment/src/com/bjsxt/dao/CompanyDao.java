package com.bjsxt.dao;

import com.bjsxt.entity.Company;

import java.util.List;

public interface CompanyDao {
    /**
     * 查询所有公司信息
     * @return
     */
    public List<Company> findList();
}
