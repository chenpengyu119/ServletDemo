package com.bjsxt.servlet;

import com.bjsxt.entity.Auditing;
import com.bjsxt.entity.Employee;
import com.bjsxt.entity.Expense;
import com.bjsxt.entity.ExpenseItem;
import com.bjsxt.service.AuditingService;
import com.bjsxt.service.ExpenseService;
import com.bjsxt.service.impl.AuditingServiceImpl;
import com.bjsxt.service.impl.ExpenseServiceImpl;
import org.mockito.internal.verification.Times;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ExpenseServlet")
public class ExpenseServlet extends BaseServlet {

    private ExpenseService expenseService = new ExpenseServiceImpl();
    private AuditingService auditingService = new AuditingServiceImpl();

    /**
     * 添加报销记录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取数据
        // 报销人
        String empId = req.getParameter("empId");
        //报销明细
        String[] type = req.getParameterValues("type");
        String[] amount = req.getParameterValues("amount");
        String[] itemDesc = req.getParameterValues("itemDesc");
        List<ExpenseItem> itemList = new ArrayList<>();
        // 总报销金额
        double totalAmount = 0;
        for (int i=0;i<type.length;i++){
            ExpenseItem item = new ExpenseItem();
            // 报销类型
            item.setType(type[i]);
            // 费用说明
            item.setItemDesc(itemDesc[i]);
            // 明细金额
            double amountD = Double.valueOf(amount[i]);
            if (amount!=null && !"".equals(amount)){
                item.setAmount(amountD);
            }
            itemList.add(item);
            totalAmount+=amountD;
        }
        // 下一个审核人
        String nextAuditor = req.getParameter("nextAuditor");
        // 总备注信息
        String expDesc = req.getParameter("expDesc");
        // 报销时间
        Date expTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

        try {
            expTime = format.parse(format.format(expTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 最后一次审核结果
        String lastResult = null;
        // 当前状态
        String status = "0";
        Expense expense = new Expense();

        expense.setEmpId(empId);
        expense.setTotalAmount(totalAmount);
        expense.setExpTime(expTime);
        expense.setExpDesc(expDesc);
        expense.setNextAuditor(nextAuditor);
        expense.setLastResult(lastResult);
        expense.setStatus(status);
        expense.setEmpId(empId);
        Employee emp = (Employee) req.getSession().getAttribute("emp");
        expense.setEmp(emp);
        expense.setItemList(itemList);
        // 因为业务层有多个操作，所以如果返回n，不能代表结果。所以直接try _ catch,通过是否报异常判断是否失败
        try {
            expenseService.save(expense);
            // 成功，页面跳转
            resp.sendRedirect(req.getContextPath()+"/myExpense.html");
        }catch (Exception e){
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            // 失败，页面跳转
            req.getRequestDispatcher("/expense/expenseAdd.jsp").forward(req, resp);
        }
    }

    /**
     * 获取待审数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void toAudit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Employee emp =(Employee) req.getSession().getAttribute("emp");
        String nextAudit = emp.getEmpId();

        // 调用业务层
        List<Expense> expList = expenseService.findListByNextAudit(nextAudit);
        if (expList!=null && expList.size()!=0){
            req.setAttribute("expList", expList);
        }else {
            req.setAttribute("tip", "没有数据");
        }
        req.getRequestDispatcher("/expense/toAudit.jsp").forward(req, resp);
    }

    /**
     * 获取详细报销数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void getDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 获取数据
        String expIdStr = req.getParameter("expId");
        int expId = 0;
        if (expIdStr!=null&& !"".equals(expIdStr)){
            expId = Integer.parseInt(expIdStr);
        }
        List<ExpenseItem> itemList = expenseService.findListByExpId(expId);

        // 返回数据 跳转页面
        req.setAttribute("itemList", itemList);
        req.getRequestDispatcher("/expense/expenseDetail.jsp").forward(req, resp);
    }

    /**
     * 添加审核记录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void addAudit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取数据
        String result = req.getParameter("result");
        String auditdesc = req.getParameter("auditDesc");
        Employee emp = (Employee)req.getSession().getAttribute("emp");
        String empId = emp.getEmpId();
        String expIdStr = req.getParameter("expId");
        String totalAmountStr = req.getParameter("totalAmount");
        // 报销人编号
        String reimburseEmp = req.getParameter("empId");
        double totalAmount = 0;


        if (totalAmountStr!=null&&!"".equals(totalAmountStr)){
            totalAmount = Double.parseDouble(totalAmountStr);
        }
        int expId = 0;
        if (expIdStr!=null){
            expId = Integer.parseInt(expIdStr);
        }

        Timestamp time = new Timestamp(System.currentTimeMillis());
        Auditing auditing = new Auditing();
        auditing.setExpId(expId);
        auditing.setEmpId(empId);
        auditing.setResult(result);
        auditing.setAuditDesc(auditdesc);
        auditing.setTime(time);
        Expense exp = new Expense();
        exp.setTotalAmount(totalAmount);
        auditing.setExpense(exp);
        // 多表操作
        try {
            auditingService.add(auditing,reimburseEmp);
            // 返回数据
            resp.getWriter().println("OK");
        }catch (Exception e){
            // 返回数据
            resp.getWriter().println("error");
        }

    }

    /**
     * 获取收入数据以便作图
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void getInData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 调用业务层查数据
        String dataStr = expenseService.getInData();
        resp.getWriter().println(dataStr);
    }
}
