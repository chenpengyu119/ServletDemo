package com.bjsxt.servlet;

import com.bjsxt.entity.Department;
import com.bjsxt.entity.Position;
import com.bjsxt.service.PositionService;
import com.bjsxt.service.impl.DepartmentServiceImpl;
import com.bjsxt.service.impl.PositionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PositionServlet")
public class PositionServlet extends BaseServlet {
    private PositionService posService = new PositionServiceImpl();
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取数据
        Object posIdObj = req.getParameter("posId");
        int posId = 0;
        if (posIdObj != null) {
            posId = Integer.parseInt((String) posIdObj);
        }
        String pname = req.getParameter("pname");
        String pdesc = req.getParameter("pdesc");
        // 调用业务层处理数据
        int n= posService.add(new Position(posId, pname, pdesc));
        // 返回数据到视图层
        if (n > 0) {
            // 重定向
            System.out.println("contextPath:" + req.getContextPath());
            resp.sendRedirect(req.getContextPath() + "/servlet/PositionServlet?method=showAll");
        } else {
            // 转发
            req.setAttribute("error", "添加失败");
            req.getRequestDispatcher("/system/positionAdd.jsp").forward(req, resp);
        }
    }

    public void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 调用业务层处理数据
        List<Position> posList = posService.findAll();
        // 返回数据到视图
        // 转发
        req.setAttribute("posList", posList);
        req.getRequestDispatcher("/system/positionList.jsp").forward(req, resp);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取数据
        Object posIdObj = req.getParameter("posId");
        int posId = 0;
        if (posIdObj != null) {
            posId = Integer.parseInt((String) posIdObj);
            int n = new PositionServiceImpl().delete(posId);
            // 重定向到查询操作
            if (n>0){
                resp.sendRedirect("/sxtoa/servlet/PositionServlet?method=showAll");
            }
        }else {
            // 转发
            req.setAttribute("error", "删除失败");
            req.getRequestDispatcher("/system/positionList.jsp").forward(req, resp);
        }
    }

    public void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取数据
        Object posIdNoObj = req.getParameter("posId");
        int posId = 0;
        if (posIdNoObj != null) {
            posId = Integer.parseInt((String) posIdNoObj);
            Position pos = new PositionServiceImpl().findById(posId);
            if (pos!=null){
                // 转发到修改界面
                req.setAttribute("pos", pos);
                req.getRequestDispatcher("/system/positionUpdate.jsp").forward(req, resp);
            }

        }else {
            // 转发
            req.setAttribute("error", "修改失败");
            req.getRequestDispatcher("/system/positionList.jsp").forward(req, resp);
        }
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取数据
        Object posIdObj = req.getParameter("posId");
        int posId = 0;
        if (posIdObj != null) {
            posId = Integer.parseInt((String) posIdObj);
        }
        String pname = req.getParameter("pname");
        String pdesc = req.getParameter("pdesc");
        Position pos = new Position(posId, pname, pdesc);
        // 处理数据
        int n = new PositionServiceImpl().update(pos);
        if (n>0){
            // 修改成功，重定向到showAll
            resp.sendRedirect("/sxtoa/servlet/PositionServlet?method=showAll");
        }else {
            // 携带id到修改前的查询操作Servlet
            req.setAttribute("posId", posId);
            req.getRequestDispatcher("servlet/PositionServlet?method=findById").forward(req, resp);
        }

    }


}
