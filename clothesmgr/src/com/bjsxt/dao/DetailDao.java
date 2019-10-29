package com.bjsxt.dao;

import com.bjsxt.entity.Detail;

public interface DetailDao {
    public Detail findByCid(int cid);

    public void add(Detail detail);
}
