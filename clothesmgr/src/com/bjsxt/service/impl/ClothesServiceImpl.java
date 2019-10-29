package com.bjsxt.service.impl;

import com.bjsxt.dao.ClothesDao;
import com.bjsxt.dao.impl.ClothesDaoImpl;
import com.bjsxt.entity.Cloth;
import com.bjsxt.service.ClothesService;

import java.util.List;

public class ClothesServiceImpl implements ClothesService {
    private ClothesDao clothesDao = new ClothesDaoImpl();
    @Override
    public List<Cloth> findByName(String cname) {
        return clothesDao.findByName(cname);
    }
}
