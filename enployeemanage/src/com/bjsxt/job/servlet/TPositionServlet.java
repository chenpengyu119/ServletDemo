package com.bjsxt.job.servlet;

import com.bjsxt.job.service.impl.TPositionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.sql.Date;
import java.text.SimpleDateFormat;

@WebServlet(name = "TPositionServlet",urlPatterns = "/servlet/TPositionServlet")
public class TPositionServlet extends BaseServlet{
    public void publish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pname = request.getParameter("position_name");
        double minsal = 0;
        if (request.getParameter("minSal") != null) {
            minsal = Double.parseDouble(request.getParameter("minSal"));
        }
        double maxsal = 0;
        if (request.getParameter("maxSal") != null) {
            maxsal = Double.parseDouble(request.getParameter("maxSal"));
        }
        String cname = request.getParameter("company");
        System.out.println("cname:---------------"+cname);

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date releasedate = new Date(new java.util.Date().getTime());
        System.out.println("参数："+pname+minsal+maxsal+releasedate);
        int res = new TPositionServiceImpl().publish(pname, minsal, maxsal, releasedate, cname);
        if (res>=0){
            response.sendRedirect("/job/job/show.jsp");
        }
    }

    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收
        String pname = request.getParameter("pname");
        int res = new TPositionServiceImpl().del(pname);
        request.getRequestDispatcher("/servlet/JobServlet?method=find").forward(request, response);
    }
}
