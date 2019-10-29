package com.bjsxt.dao;

import com.bjsxt.entity.Expense;
import com.bjsxt.entity.ExpenseItem;

import java.util.List;

public interface ExpenseDao {
    public int add(Expense expense);

    public List<Expense> findList(String nextAudit);

    public List<ExpenseItem> findItemByExpId(int expId);

    /**
     * 根据expId更新
     *
     * @param exp
     */
    public void update(Expense exp);

    public List<Object[]> findInList();
}
