package com.bjsxt.dao.impl;

import com.bjsxt.dao.ExpenseDao;
import com.bjsxt.entity.Employee;
import com.bjsxt.entity.Expense;
import com.bjsxt.entity.ExpenseItem;
import com.bjsxt.util.BeanListHandler;
import com.bjsxt.util.DbUtils;
import com.bjsxt.util.DbUtils2;
import com.bjsxt.util.SxtOaException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDaoImpl implements ExpenseDao {
    @Override
    public int add(Expense expense) {

        String sql = "insert  expense VALUES(null, ? ,? ,? ,? ,? ,? , ? );";
        Object[] params = {
                expense.getEmpId(),
                expense.getTotalAmount(),
                expense.getExpTime(),
                expense.getExpDesc(),
                expense.getNextAuditor(),
                expense.getLastResult(),
                expense.getStatus()
        };
        // 得到和service中使用的同一个连接 因为是同一个请求，所以是同一个线程，获得的连接属于 ThreadLocal中
        Connection conn = DbUtils2.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int expId = 0;
        try {
            ps = conn.prepareStatement(sql);
            int loop = 1;
            for (Object pa:params){
                ps.setObject(loop++, pa);
            }
            ps.executeUpdate();
            // 查询报销单编号
            String querySql = "select @@identity as id";
            ps = conn.prepareStatement(querySql);
            rs = ps.executeQuery();
            if (rs.next()){
                expId = rs.getInt("id");
                return expId;
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new SxtOaException(e.getMessage());
        }

        return expId;
    }

    @Override
    public List<Expense> findList(String nextAudit) {
        String sql = "select * from expense exp\n" +
                "JOIN (SELECT empid,REALNAME from employee) empl\n" +
                "on exp.EMPID = empl.empid\n" +
                "where exp.NEXTAUDITOR = ?";
        Object[] params = {nextAudit};
        BeanListHandler listHandler = new BeanListHandler() {
            @Override
            public List resultHandler(ResultSet rs) {
                List<Expense> expenseList = new ArrayList<>();
                while (true) {
                    try {
                        if (!rs.next()) {
                            break;
                        }
                        Expense expense = new Expense();
                        // 真实姓名
                        Employee emp = new Employee();
                        emp.setRealName(rs.getString("realName"));
                        expense.setEmp(emp);
                        // 总金额
                        expense.setTotalAmount(rs.getDouble("totalAmount"));
                        // 报销时间
                        expense.setExpTime(rs.getDate("expTime"));
                        // 总备注信息
                        expense.setExpDesc(rs.getString("expDesc"));
                        expense.setExpId(rs.getInt("expId"));
                        expenseList.add(expense);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        throw new SxtOaException(e.getMessage());
                    }
                }return expenseList;
            }
        };

        return DbUtils.queryGeneralCall(sql, listHandler, params);
    }

    @Override
    public List<ExpenseItem> findItemByExpId(int expId) {

        String sql = "select * from expenseitem where EXPID = ?";
        Object[] params = {expId};
        BeanListHandler<ExpenseItem> handler = new BeanListHandler<ExpenseItem>() {
            @Override
            public List<ExpenseItem> resultHandler(ResultSet rs) {
                List<ExpenseItem> itemList = new ArrayList<>();
                while (true){
                    try {
                        if (!rs.next()){break;}
                        ExpenseItem item = new ExpenseItem();
                        // 报销项编号
                        item.setItemId(rs.getInt("itemid"));
                        // 报销项类型
                        item.setType(rs.getString("type"));
                        // 报销项金额
                        item.setAmount(rs.getDouble("amount"));
                        // 报销项说明
                        item.setItemDesc(rs.getString("itemDesc"));
                        item.setExpId(expId);
                        itemList.add(item);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        throw new SxtOaException(e.getMessage());
                    }
                }
                return itemList;
            }
        };
        return DbUtils.queryGeneralCall(sql, handler, params);
    }

    @Override
    public void update(Expense exp) {

        String sql = "update expense set NEXTAUDITOR = ? , LASTRESULT = ?, STATUS = ? where expId = ?";
        Object[] params = {
                exp.getNextAuditor(),
                exp.getLastResult(),
                exp.getStatus(),
                exp.getExpId()
        };
        DbUtils2.update(sql, params);
    }

    @Override
    public List<Object[]> findInList() {

        String sql = "select * from income";
        BeanListHandler handler = new BeanListHandler() {
            @Override
            public List resultHandler(ResultSet rs) {
                List<Object[]> list = new ArrayList<>();
                while (true) {
                    try {
                        if (!rs.next()) {
                            break;
                        }
                        Object[] objects = new Object[2];
                        objects[0] = rs.getString("ICTYPE");
                        objects[1] = rs.getInt("AMOUNT");
                        list.add(objects);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }return list;
            }
        };
        return DbUtils.queryGeneralCall(sql, handler);
    }
}
