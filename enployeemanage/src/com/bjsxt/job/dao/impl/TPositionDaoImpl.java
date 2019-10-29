package com.bjsxt.job.dao.impl;

import com.bjsxt.job.dao.TPositionDao;
import com.bjsxt.job.entity.Job;
import com.bjsxt.job.entity.TCompany;
import com.bjsxt.job.entity.TPosition;
import com.bjsxt.job.utils.DbUtils;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @author pengyu
 */
public class TPositionDaoImpl implements TPositionDao {

    @Override
    public int add(String pname, double minsal, double maxsal, Date releasedate, String cname) {
        String sql = "insert into t_position (pname,minsal,maxsal,releasedate,cid)\n" +
                "VALUES(?,?,?,?,\n" +
                "(select cid  from  t_company where cname = ?)\n" +
                "\n" +
                ")";

        int res = DbUtils.update(sql, pname,minsal,maxsal,releasedate,cname);
        return res;
    }

    @Override
    public int del(String pname) {

        String sql = "DELETE FROM t_position WHERE pname = ?";
        int res = DbUtils.update(sql, pname);
        System.out.println("删除是否成功:"+(res>0?"成功":"失败"));
        return res;
    }

    public static void main(String[] args) {
        // 测试删除
        new TPositionDaoImpl().del("校长");
    }

}
