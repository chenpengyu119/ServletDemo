package com.bjsxt.dao.impl;

import com.bjsxt.dao.CompanyDao;
import com.bjsxt.entity.Company;
import com.bjsxt.util.DBUtil;
import sun.security.pkcs11.Secmod;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {
    @Override
    public List<Company> findList() {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        List<Company> companyList = new ArrayList<>();

        conn = DBUtil.getConnection();
        String sql = "select * from t_company";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()){
                Company company = new Company();
                company.setCid(rs.getInt("cid"));
                company.setCname(rs.getString("cname"));
                company.setLocation(rs.getString("location"));
                companyList.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(rs, st, conn);
        };
        return companyList;
    }
}
