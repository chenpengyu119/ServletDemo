package com.bjsxt.service;

import com.bjsxt.entity.Cloth;

import java.util.List;

public interface ClothesService {
    /**
     * 根据服装名称查找服装信息
     * @param cname
     * @return
     */
    public List<Cloth> findByName(String cname);
}
