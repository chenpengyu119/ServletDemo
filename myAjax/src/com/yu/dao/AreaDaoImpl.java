package com.yu.dao;

import com.yu.entity.Area;
import com.yu.util.DbUtils;

import java.util.List;

/**
 * @author pengyu
 */
public class AreaDaoImpl {
    public List<Area> find(int parentId){
        String sql = "select * from area where parentid = "+parentId;
        List<Area> areaList = DbUtils.queryGeneral(sql, Area.class);
        for (Area area : areaList){
            System.out.println(area.getAreaName());
        }
        return areaList;
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        new AreaDaoImpl().find(0);
    }
}
