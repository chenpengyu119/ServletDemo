package com.bjsxt.service;

import com.bjsxt.entity.Expense;
import com.bjsxt.entity.ExpenseItem;

import java.util.List;

public interface ExpenseService {
    public void save(Expense expense);

    public List<Expense> findListByNextAudit(String nextAudit);

    public List<ExpenseItem> findListByExpId(int expId);

    public void update(Expense exp);

    /**
     * 获取收入数据
     * @return
     */
    public String getInData();
}
