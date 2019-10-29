package com.bjsxt.entity;

public class Detail {
    private int did;
    private String dcolor;
    private float dprice;
    private float dsize;
    private int cid;

    private Cloth cloth;


    public Detail() {
    }

    public Detail(int did, String dcolor, float dprice, float dsize, int cid, Cloth cloth) {
        this.did = did;
        this.dcolor = dcolor;
        this.dprice = dprice;
        this.dsize = dsize;
        this.cid = cid;
        this.cloth = cloth;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDcolor() {
        return dcolor;
    }

    public void setDcolor(String dcolor) {
        this.dcolor = dcolor;
    }

    public float getDprice() {
        return dprice;
    }

    public void setDprice(float dprice) {
        this.dprice = dprice;
    }

    public float getDsize() {
        return dsize;
    }

    public void setDsize(float dsize) {
        this.dsize = dsize;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Cloth getCloth() {
        return cloth;
    }

    public void setCloth(Cloth cloth) {
        this.cloth = cloth;
    }

    @Override
    public String toString() {
        String str =  "Detail{" +
                "did=" + did +
                ", dcolor='" + dcolor + '\'' +
                ", dprice=" + dprice +
                ", dsize=" + dsize +
                ", cid=" + cid +
                '}';
        if (cloth!=null){
            str+=   ", cloth=" + cloth;
        }
        return str;
    }
}
