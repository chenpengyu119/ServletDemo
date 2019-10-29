package com.bjsxt.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 测试转发和重定向
 */
@WebServlet(name = "ForwardServlet",urlPatterns = "/servlet/ForwardServlet")
public class ForwardServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 请求转发
        // 由request实现
        // 使用根路径 ， /代表当前项目
        // 注意：需要写forward  地址栏url不改变
       // req.getRequestDispatcher("/servlet/FirstServlet").forward(req, resp);
        // 使用重定向 通过resp实现  /代表当前服务器
       //  resp.sendRedirect("/myservlet/servlet/FirstServlet");

        System.out.println("访问ForwardServlet成功");
        // 通过请求转发访问Web-inf下面的文件  不需要写web，因为它和项目路径等同
        req.getRequestDispatcher("/WEB-INF/testVisit.jsp").forward(req, resp);
    }
}
