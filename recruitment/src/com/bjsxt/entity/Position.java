package com.bjsxt.entity;

import java.util.Date;

public class Position {
    private int pid;
    private String pname;
    private double minsal;
    private double maxsal;
    private Date releasedate;
    private int cid;
    private Company company;

    public Position() {
    }

    public Position(int pid, String pname, double minsal, double maxsal, Date releasedate, int cid, Company company) {
        this.pid = pid;
        this.pname = pname;
        this.minsal = minsal;
        this.maxsal = maxsal;
        this.releasedate = releasedate;
        this.cid = cid;
        this.company = company;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        String str = "Position{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", minsal=" + minsal +
                ", maxsal=" + maxsal +
                ", releasedate=" + releasedate +
                ", cid=" + cid +
                '}';
        if (company!=null){
            str+=", company=" + company ;
        }
        return  str;
    }
}
