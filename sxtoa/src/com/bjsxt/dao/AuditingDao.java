package com.bjsxt.dao;

import com.bjsxt.entity.Auditing;

public interface AuditingDao {

    /**
     * 添加一条审核记录
     * @param auditing
     */
    public void add(Auditing auditing);
}
