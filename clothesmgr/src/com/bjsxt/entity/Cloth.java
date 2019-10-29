package com.bjsxt.entity;

public class Cloth {
    private int cid;
    private String cname;
    private String ebrand;
    private String cgender;

    public Cloth() {
    }

    public Cloth(int cid, String cname, String ebrand, String cgender) {
        this.cid = cid;
        this.cname = cname;
        this.ebrand = ebrand;
        this.cgender = cgender;
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

    public String getEbrand() {
        return ebrand;
    }

    public void setEbrand(String ebrand) {
        this.ebrand = ebrand;
    }

    public String getCgender() {
        return cgender;
    }

    public void setCgender(String cgender) {
        this.cgender = cgender;
    }

    @Override
    public String toString() {
        return "Cloth{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", ebrand='" + ebrand + '\'' +
                ", cgender='" + cgender + '\'' +
                '}';
    }
}
