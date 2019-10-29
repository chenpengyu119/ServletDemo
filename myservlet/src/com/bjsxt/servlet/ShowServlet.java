package com.bjsxt.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ShowServlet",urlPatterns = "/servlet/show")
public class ShowServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        HttpSession hs = req.getSession();
        Object obj = hs.getAttribute("uname");
        if (obj != null){
            resp.getWriter().write("用户名："+(String)obj);
        }else {
            // 可能在success.jsp页面待的时间太久，session失效，重定向
            resp.sendRedirect("/myservlet/ck");
        }
    }
}
