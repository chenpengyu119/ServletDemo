package com.bjsxt.entity;

public class Company {
    private int cid;
    private String cname;
    private String location;

    public Company() {
    }

    public Company(int cid, String cname, String location) {
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

    @Override
    public String toString() {
        return "Company{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
