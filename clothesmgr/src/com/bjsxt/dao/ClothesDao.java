package com.bjsxt.dao;

import com.bjsxt.entity.Cloth;

import java.util.List;

public interface ClothesDao {
    public List<Cloth> findByName(String cname);

    public int add(Cloth cloth);
}
