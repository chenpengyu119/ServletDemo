package com.yu.entity;

/**
 * @author pengyu
 */
public class Area {
    private int areaId;
    private String areaName;
    private int parentId;
    private int areaLevel;
    private int status;

    public Area() {
    }

    public Area(int areaId, String areaName, int parentId, int areaLevel, int status) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.parentId = parentId;
        this.areaLevel = areaLevel;
        this.status = status;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(int areaLevel) {
        this.areaLevel = areaLevel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", parentId=" + parentId +
                ", areaLevel=" + areaLevel +
                ", status=" + status +
                '}';
    }
}
