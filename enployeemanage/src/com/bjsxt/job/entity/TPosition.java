package com.bjsxt.job.entity;

import java.sql.Date;

/**
 * 对应数据库中职位表
 * @author pengyu
 */
public class TPosition {
    /**
     * 职位编号
     */
    private int pid;
    /**
     * 职位名称
     */
    private String pname;
    /**
     * 最低工资
     */
    private double minsal;
    /**
     * 最高工资
     */
    private double maxsal;
    /**
     * 发布日期
     */
    private Date releasedate;
    /**
     * 公司编号
     */
    private int cid;

    public TPosition() {
    }

    public TPosition(int pid, String pname, double minsal, double maxsal, Date releasedate, int cid) {
        this.pid = pid;
        this.pname = pname;
        this.minsal = minsal;
        this.maxsal = maxsal;
        this.releasedate = releasedate;
        this.cid = cid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getMinsal() {
        return minsal;
    }

    public void setMinsal(double minsal) {
        this.minsal = minsal;
    }

    public double getMaxsal() {
        return maxsal;
    }

    public void setMaxsal(double maxsal) {
        this.maxsal = maxsal;
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "TPosition{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", minsal=" + minsal +
                ", maxsal=" + maxsal +
                ", releasedate=" + releasedate +
                ", cid=" + cid +
                '}';
    }
}
