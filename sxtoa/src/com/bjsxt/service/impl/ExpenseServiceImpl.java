package com.bjsxt.service.impl;

import com.bjsxt.dao.ExpenseDao;
import com.bjsxt.dao.impl.ExpenseDaoImpl;
import com.bjsxt.entity.Employee;
import com.bjsxt.entity.Expense;
import com.bjsxt.entity.ExpenseItem;
import com.bjsxt.service.ExpenseService;
import com.bjsxt.util.DbUtils2;
import com.bjsxt.util.SxtOaException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseDao expenseDao = new ExpenseDaoImpl();
    @Override
    public void save(Expense expense) {
        // 添加报销单 得到和save中使用的同一个连接 因为是同一个请求，所以是同一个线程，获得的连接属于 ThreadLocal中
        Connection conn = DbUtils2.getConn();
        try {
            conn.setAutoCommit(false);
            // 因为报销单编号是自增的，所以需要添加结束查询编号
            int  expId = expenseDao.add(expense);
            List<ExpenseItem> itemList =  expense.getItemList();
            String sql = "insert expenseitem VALUES(null,? , ?, ?, ? );";
            // 添加报销明细
            PreparedStatement ps = null;
            int loop = 1;
            for (ExpenseItem item:itemList){
                ps = conn.prepareStatement(sql);
                ps.setInt(1, expId);
                ps.setString(2, item.getType());
                ps.setDouble(3, item.getAmount());
                ps.setString(4, item.getItemDesc());
                ps.executeUpdate();
                conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new SxtOaException(e.getMessage());
            }
            throw new SxtOaException(e.getMessage());
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }

    }

    @Override
    public List<Expense> findListByNextAudit(String nextAudit) {
        return expenseDao.findList(nextAudit);
    }

    @Override
    public List<ExpenseItem> findListByExpId(int expId) {
        return expenseDao.findItemByExpId(expId);
    }

    @Override
    public void update(Expense exp) {
        expenseDao.update(exp);
    }

    @Override
    public String getInData() {
        List<Object[]> list = expenseDao.findInList();
        // [ ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"] , [5, 20, 36, 10, 10, 20] ]
        StringBuilder type = new StringBuilder("[[");
        StringBuilder data = new StringBuilder("[");
        int loop = 0;
        for (Object[] objects : list){
            if (loop==list.size()-1){
                type.append("\"" + objects[0] + "\"");
                data.append(objects[1]);
            }else {
                type.append("\"" + objects[0] + "\",");
                data.append(objects[1] + ",");
            }
            loop++;
        }
        type.append("],");
        data.append("]]");
        return type.toString()+data.toString();
    }


}
