package com.bjsxt.util;

import java.sql.ResultSet;
import java.util.List;

public  interface BeanListHandler<T>{
    public List<T> resultHandler(ResultSet rs);
}
