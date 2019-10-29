package com.bjsxt.servlet;

import com.bjsxt.entity.Department;
import com.bjsxt.entity.Employee;
import com.bjsxt.entity.Position;
import com.bjsxt.service.DepartmentService;
import com.bjsxt.service.EmployeeService;
import com.bjsxt.service.PositionService;
import com.bjsxt.service.impl.DepartmentServiceImpl;
import com.bjsxt.service.impl.EmployeeServiceImpl;
import com.bjsxt.service.impl.PositionServiceImpl;
import com.bjsxt.util.PageBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "EmployeeServlet")
public class EmployeeServlet extends BaseServlet {

    private DepartmentService deptService = new DepartmentServiceImpl();
    private EmployeeService empService = new EmployeeServiceImpl();
    private PositionService posService = new PositionServiceImpl();


    public void getDept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        // 查询所有部门名称
        List<Department> deptList = deptService.showAll();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String deptListStr = gson.toJson(deptList);
        resp.getWriter().println(deptListStr);
    }

    public void getPos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        // 查询所有岗位名称
        List<Position> posList = posService.findAll();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String posListStr = gson.toJson(posList);
        resp.getWriter().println(posListStr);
    }

    public void getMgr(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 查询所有上级姓名
        List<Employee> empList = empService.findByEmptype(2);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String empListStr = gson.toJson(empList);
        resp.getWriter().println(empListStr);
    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 获取数据
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        // 用户名
        String empId = req.getParameter("empId");
        // 真实姓名
        String realName = req.getParameter("realName");
        // 密码
        String password = req.getParameter("password");
        // 性别
        String sex = req.getParameter("sex");
        // 出生日期
        Object birthdateObj = req.getParameter("birthdate");
        Date birthdate = null;
        if (birthdateObj!=null){
            try {
                birthdate = new Date((format.parse((String)birthdateObj).getTime()));
            } catch (ParseException e) {
                System.err.println("日期格式转换错误--出生日期");
            }
        }
        // 入职时间
        Object hiredateObj = req.getParameter("hiredate");
        Date hiredate = null;
        if (hiredateObj!=null){
            try {
                hiredate = new Date((format.parse((String)hiredateObj).getTime()));
            } catch (ParseException e) {
                System.err.println("日期格式转换错误--入职时间");
            }
        }
        // 是否在职
        Object ondutyObj = req.getParameter("onduty");
        int onduty = 0;
        if (ondutyObj!=null){
            onduty = Integer.parseInt((String)ondutyObj);
        }
        // 员工类型
        Object empTypeObj = req.getParameter("empType");
        int empType = 0;
        if (empTypeObj!=null){
            empType = Integer.parseInt((String)empTypeObj);
        }
        // 所属部门
        Object deptNoObj = req.getParameter("deptNo");
        int deptNo = 0;
        if (deptNoObj!=null){
            deptNo = Integer.parseInt((String)deptNoObj);
        }
        // 从事岗位
        Object posIdObj = req.getParameter("posId");
        int posId = 0;
        if (posIdObj!=null){
            posId = Integer.parseInt((String)posIdObj);
        }

        // 直接上级
        String mgrId = req.getParameter("mgrId");
        // 联系方式
        String phone = req.getParameter("phone");
        // QQ号
        String qq = req.getParameter("qq");
        // 紧急联系人 emerContactPerson
        String emerContactPerson = req.getParameter("emerContactPerson");
        // 身份证号
        String idCard = req.getParameter("idCard");
        Employee emp = new Employee(empId, mgrId, posId, deptNo,
                password, realName, sex, birthdate,
                hiredate, null, onduty, qq, empType,
                phone, emerContactPerson, idCard,null,null);
        int n = empService.add(emp);
        if (n>0){
            resp.sendRedirect(req.getContextPath()+"/system/empList.jsp");

        }else {
            req.setAttribute("error", "添加员工失败");
            req.getRequestDispatcher("/system/empAdd.jsp").forward(req, resp);
        }
    }

    /**
     * 显示分页数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 获取分页参数
        String indexStr = req.getParameter("index");
        // 默认当前页为第一页
        int index = 1;
        if (indexStr!=null&&!"".equals(indexStr)){
            index = Integer.parseInt(indexStr);
        }
        // 每页几条数据
        String sizeStr = req.getParameter("size");
        // 默认每页显示5条数据
        int size = 5;
        if (sizeStr!=null&&!"".equals(sizeStr)){
            size = Integer.parseInt(sizeStr);
        }


        // 获取条件查询数据
        String empId = req.getParameter("empId");
        if (empId==null){
            empId="";
        }

        Object deptNoObj = req.getParameter("deptNo");
        int deptNo = 0;
        if (deptNoObj!=null && !"".equals(deptNoObj)){
            deptNo = Integer.parseInt(deptNoObj.toString());
        }
        Object ondutyObj = req.getParameter("onduty");
        int onduty= 0;
        if (ondutyObj!=null && !"".equals(onduty)){
            onduty = Integer.parseInt(ondutyObj.toString());
        }
        String hiredate = req.getParameter("hiredate");
        if (hiredate==null){
            hiredate="";
        }

        PageBean<Employee> pageBean = new PageBean<>();
        pageBean.setSize(size);
        pageBean.setIndex(index);

        Object[] param = {empId,deptNo,onduty,hiredate};
        empService.list(pageBean,param);

        // 查询所有部门名称
        List<Department> deptList = deptService.showAll();

        // 共享数据
        String pageStr = new Gson().toJson(pageBean);
        String deptListStr = new Gson().toJson(deptList);
        String split = "|||";
        resp.getWriter().println(pageStr+split+deptListStr);
    }

    /**
     * 修改前的查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 获取数据
        String empId = req.getParameter("empId");

        // 查询到要修改的用户的信息
        Employee emp = empService.findById(empId);
        // 查询部门和上级信息
        List<Department> deptList = deptService.showAll();
        List<Employee> empList = empService.findByEmptype(2);

        // 转发
        if (emp!=null){
            req.setAttribute("empList", empList);
            req.setAttribute("deptList", deptList);
            req.setAttribute("empUpdt", emp);
            req.getRequestDispatcher("/system/empUpdate.jsp").forward(req, resp);
        }
    }

    /**
     * 修改用户信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        // 接收数据
        // 用户名
        String empId = req.getParameter("empId");
        // 真实姓名
        String realName = req.getParameter("realName");
        // 密码
        String password = req.getParameter("password");
        // 性别
        String sex = req.getParameter("sex");
        // 出生日期
        Object birthdateObj = req.getParameter("birthdate");
        Date birthdate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (birthdateObj!=null){
            try {
                birthdate = new Date((sdf.parse((String)birthdateObj)).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        // 入职时间
        Object hiredateObj = req.getParameter("hiredate");
        Date hiredate = null;
        if (hiredateObj!=null){
            try {
                hiredate = new Date((sdf.parse((String)hiredateObj)).getTime());
            } catch (ParseException e) {
               e.printStackTrace();
            }
        }
        // 是否在职
        Object ondutyObj = req.getParameter("onduty");
        int onduty = 0;
        if (ondutyObj!=null){
            onduty = Integer.parseInt((String)ondutyObj);
        }
        // 员工类型
        Object empTypeObj = req.getParameter("empType");
        int empType = 0;
        if (empTypeObj!=null){
            empType = Integer.parseInt((String)empTypeObj);
        }
        // 所属部门
        Object deptNoObj = req.getParameter("deptNo");
        int deptNo = 0;
        if (deptNoObj!=null){
            deptNo = Integer.parseInt((String)deptNoObj);
        }
        // 从事岗位
        Object posIdObj = req.getParameter("posId");
        int posId = 0;
        if (posIdObj!=null){
            posId = Integer.parseInt((String)posIdObj);
        }

        // 直接上级
        String mgrId = req.getParameter("mgrId");
        // 联系方式
        String phone = req.getParameter("phone");
        // QQ号
        String qq = req.getParameter("qq");
        // 紧急联系人 emerContactPerson
        String emerContactPerson = req.getParameter("emerContactPerson");
        // 身份证号
        String idCard = req.getParameter("idCard");
        Employee emp = new Employee(empId, mgrId, posId, deptNo,
                password, realName, sex, birthdate,
                hiredate, null, onduty, qq, empType,
                phone, emerContactPerson, idCard,null,null);
        int n = empService.update(emp);

        if (n>0){
            resp.sendRedirect(req.getContextPath()+"/system/empList.jsp");

        }else {
            req.setAttribute("error", "修改失败");
            req.getRequestDispatcher("/system/empList.jsp").forward(req, resp);
        }


    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        // 接收数据
        String empId = req.getParameter("empId");
        String password = req.getParameter("password");
        /*String verifyCode = (String) req.getSession().getAttribute("randStr");
        String inVerifyCode = req.getParameter("verifyCode");
        if (!verifyCode.equals(inVerifyCode)){
            req.setAttribute("error", "验证码不正确");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }*/


        Employee emp = empService.login(empId, password);
        if (emp==null){
            req.setAttribute("error", "用户名或密码错误");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }else {
            req.getSession().setAttribute("emp", emp);
            resp.sendRedirect(req.getContextPath()+"/main.html");
        }
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        req.getSession().invalidate();

        resp.sendRedirect(req.getContextPath()+"/login.jsp");

    }





}
