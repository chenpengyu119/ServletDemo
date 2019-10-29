package com.bjsxt.servlet;

import com.bjsxt.entity.Department;
import com.bjsxt.service.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DepartmentServlet")
public class DepartmentServlet extends BaseServlet {

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求数据
        Object deptNoObj = req.getParameter("deptNo");
        int deptNo = 0;
        if (deptNoObj != null) {
            deptNo = Integer.parseInt((String) deptNoObj);
        }
        String deptName = req.getParameter("deptName");
        String location = req.getParameter("location");
        // 调用业务层处理数据
        Department dept = new Department(deptNo, deptName, location);
        int n = new DepartmentServiceImpl().add(dept);
        // 返回数据到视图层
        if (n > 0) {
            // 重定向
            System.out.println("contextPath:" + req.getContextPath());
            resp.sendRedirect(req.getContextPath() + "/servlet/DepartmentServlet?method=showAll");
        } else {
            // 转发
            req.setAttribute("error", "添加失败");
            req.getRequestDispatcher("/system/deptAdd.jsp").forward(req, resp);
        }
    }

    public void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求数据

        // 调用业务层处理数据
        List<Department> deptList = new DepartmentServiceImpl().showAll();
        // 返回数据到视图
        // 转发
        req.setAttribute("deptList", deptList);
        req.getRequestDispatcher("/system/deptList.jsp").forward(req, resp);
    }
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取数据
        Object deptNoObj = req.getParameter("deptNo");
        int deptNo = 0;
        if (deptNoObj != null) {
            deptNo = Integer.parseInt((String) deptNoObj);
            int n = new DepartmentServiceImpl().delete(deptNo);
            // 重定向到查询操作
            if (n>0){
                resp.sendRedirect("/sxtoa/servlet/DepartmentServlet?method=showAll");
            }
        }else {
            // 转发
            req.setAttribute("error", "删除失败");
            req.getRequestDispatcher("/system/deptList.jsp").forward(req, resp);
        }
    }

    /**
     * 修改前的查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取数据
        Object deptNoObj = req.getParameter("deptNo");
        int deptNo = 0;
        if (deptNoObj != null) {
            deptNo = Integer.parseInt((String) deptNoObj);
            Department dept = new DepartmentServiceImpl().findById(deptNo);
            if (dept!=null){
                // 转发到修改界面
                req.setAttribute("dept", dept);
                req.getRequestDispatcher("/system/deptUpdate.jsp").forward(req, resp);
            }

        }else {
            // 转发
            req.setAttribute("error", "修改失败");
            req.getRequestDispatcher("/system/deptList.jsp").forward(req, resp);
        }
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取数据
        Object deptNoObj = req.getParameter("deptNo");
        int deptNo = 0;
        if (deptNoObj != null) {
            deptNo = Integer.parseInt((String) deptNoObj);
        }
        String deptName = req.getParameter("deptName");
        String location = req.getParameter("location");
        Department dept = new Department(deptNo, deptName, location);
        // 处理数据
        int n = new DepartmentServiceImpl().update(dept);
        if (n>0){
            // 修改成功，重定向到showAll
            resp.sendRedirect("/sxtoa/servlet/DepartmentServlet?method=showAll");
        }else {
            // 携带id到修改前的查询操作Servlet
            req.setAttribute("deptNo", deptNo);
            req.getRequestDispatcher("servlet/DepartmentServlet?method=findById").forward(req, resp);
        }

    }




}
