package com.bjsxt.job.entity;

/**
 * 对应数据库中公司表
 * @author pengyu
 */
public class TCompany {
    /**
     * 公司编号
     */
    private int cid;
    /**
     * 公司名称
     */
    private String cname;
    /**
     * 公司地址
     */
    private String location;

    public TCompany() {
    }

    public TCompany(int cid, String cname, String location) {
        this.cid = cid;
        this.cname = cname;
        this.location = location;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
