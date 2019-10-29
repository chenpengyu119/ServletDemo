package com.bjsxt.service;

import com.bjsxt.entity.Detail;

public interface DetailService {
    public Detail findByCid(int cid);

    public void add(Detail detail);
}
