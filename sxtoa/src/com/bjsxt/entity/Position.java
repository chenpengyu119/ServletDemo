package com.bjsxt.entity;

/**
 * 岗位实体
 * @author pengyu
 */
public class Position {
    // 岗位编号
    private int posId;
    // 岗位名称
    private String pname;
    // 岗位描述
    private String pdesc;

    public Position() {
    }

    public Position(int posId, String pname, String pdesc) {
        this.posId = posId;
        this.pname = pname;
        this.pdesc = pdesc;
    }

    public int getPosId() {
        return posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    @Override
    public String toString() {
        return "Position{" +
                "posId=" + posId +
                ", pname='" + pname + '\'' +
                ", pdesc='" + pdesc + '\'' +
                '}';
    }
}
