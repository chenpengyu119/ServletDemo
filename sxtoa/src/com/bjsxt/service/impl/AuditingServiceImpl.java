package com.bjsxt.service.impl;

import com.bjsxt.dao.AuditingDao;
import com.bjsxt.dao.PaymentDao;
import com.bjsxt.dao.impl.AuditingDaoImpl;
import com.bjsxt.dao.impl.PaymentDaoImpl;
import com.bjsxt.entity.Auditing;
import com.bjsxt.entity.Employee;
import com.bjsxt.entity.Expense;
import com.bjsxt.entity.Payment;
import com.bjsxt.service.AuditingService;
import com.bjsxt.service.EmployeeService;
import com.bjsxt.service.ExpenseService;
import com.bjsxt.util.Constants;
import com.bjsxt.util.DbUtils2;
import com.bjsxt.util.SxtOaException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AuditingServiceImpl implements AuditingService {

    // 审核通过的状态
    private final String PASS = "通过";
    private final String REJECT = "拒绝";
    private final String BACK = "打回";
    // 财务部门编号
    private int deptNoFinance = 4;
    // 需要总裁批准的额度下限
    private double topMoney = 2500;

    private AuditingDao auditingDao = new AuditingDaoImpl();
    private EmployeeService empService = new EmployeeServiceImpl();
    private ExpenseService expService = new ExpenseServiceImpl();
    private PaymentDao pmDao = new PaymentDaoImpl();
    @Override
    public void add(Auditing auditing,String reimburseEmp) {

        Connection conn = DbUtils2.getConn();
        try {
            conn.setAutoCommit(false);
            // 是财务吗 审核人的部门是财务部吗
            // 根据empId查部门编号
            Employee emp = empService.findById(auditing.getEmpId());
            String result = auditing.getResult();
            if (emp.getDeptNo()==deptNoFinance){
                // 添加财务支出
                Payment pm = new Payment();
                // 报销人编号
                pm.setEmpId(reimburseEmp);
                pm.setPayEmpId(auditing.getEmpId());
                pm.setExpId(auditing.getExpId());
                pm.setAmount(auditing.getExpense().getTotalAmount());
                pm.setPayTime(new Date());
                pmDao.add(pm);
                // 修改报销单状态
                Expense exp = new Expense();
                // 设置报销单编号
                exp.setExpId(auditing.getExpId());
                // 下一个审核人
                exp.setNextAuditor(null);
                // 最近一次审核结果
                exp.setLastResult(auditing.getResult());
                // 审核状态
                exp.setStatus(Constants.EXP_AUDIT_PASS);
                expService.update(exp);
            }else {
                // 审核未通过
                if (!PASS.equals(result)){
                    // 添加审核记录
                    // 修改报销单状态
                    if (REJECT.equals(result)){ // 拒绝
                        doAudit(auditing, null, Constants.EXP_AUDIT_REJECT);
                    }else if (BACK.equals(result)){// 打回
                        doAudit(auditing, null, Constants.EXP_AUDIT_BACK);
                    }
                }else {
                    if (!(auditing.getExpense().getTotalAmount()>topMoney)){
                        // 金额未超过2500
                        // 添加审核记录
                        // 修改报销单状态 为审核中
                        doAudit(auditing, Constants.SXT_CFO, Constants.EXP_AUDIT_AUDITING);
                    }else {// 超过2500
                        if (Constants.SXT_CEO.equals(auditing.getEmpId())){
                            // 是总裁
                            // 添加审核记录
                            // 修改报销单状态
                            doAudit(auditing, Constants.SXT_CFO, Constants.EXP_AUDIT_AUDITING);
                        }else {// 不是总裁
                            // 添加审核记录
                            // 修改报销单状态
                            doAudit(auditing, Constants.SXT_CEO, Constants.EXP_AUDIT_AUDITING);
                        }
                    }
                }
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                throw new SxtOaException(e1.getMessage());
            }
            throw new SxtOaException(e.getMessage());
        }finally {
            DbUtils2.close(null, null, conn);
        }
    }

    private void doAudit(Auditing auditing,String nextAuditor,String status){
        // 添加审核记录
        auditingDao.add(auditing);
        // 修改报销单状态
        Expense exp = new Expense();
        // 设置报销单编号
        exp.setExpId(auditing.getExpId());
        // 下一个审核人
        exp.setNextAuditor(nextAuditor);
        // 最近一次审核结果
        exp.setLastResult(auditing.getResult());
        // 审核状态
        exp.setStatus(status);
        expService.update(exp);
    }
}
