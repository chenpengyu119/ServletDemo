package com.bjsxt.util;

import java.sql.ResultSet;

public interface BeanHandler<T> {
    public T resultHandler(ResultSet rs);
}
