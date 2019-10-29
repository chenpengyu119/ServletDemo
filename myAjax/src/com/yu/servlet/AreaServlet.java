package com.yu.servlet;

import com.google.gson.Gson;
import com.yu.dao.AreaDaoImpl;
import com.yu.entity.Area;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AreaServlet",urlPatterns = "/servlet/AreaServlet")
public class AreaServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String str = "parentId";
        Object parentIdObj = req.getParameter(str);
        int parentId = 0;
        if (parentIdObj!=null){
            parentId = Integer.parseInt((String)parentIdObj);
        }else {
            resp.getWriter().println("没有查找到数据");
            return;
        }
        List<Area> areaList = new AreaDaoImpl().find(parentId);
        // 转json
        String areaListStr = new Gson().toJson(areaList);

        resp.getWriter().println(areaListStr);

    }
}
