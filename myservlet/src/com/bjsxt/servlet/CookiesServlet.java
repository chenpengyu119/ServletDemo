package com.bjsxt.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 该Servlet是cookies的有效路径，也就是说只有访问该页面才会携带Cookies
 * 用户访问该页面后，先判断是否有cookies，没有则直接进登录界面
 * 如果有Cookies，则判断用户id是否存在，存在则直接进主页，否则进登录页面
 */
@WebServlet(name = "CookiesServlet", urlPatterns = {"/servlet/ck"})
public class CookiesServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 设置请求编码
        req.setCharacterEncoding("utf-8");
        // 设置响应编码
        resp.setContentType("text/html,charset=utf-8");

        // 获取cookies
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            String uname = null;
            // 遍历
            for (Cookie c : cookies) {
                String uid = c.getName();
                // 找到键为uid的值
                if ("uid".equals(uid)) {
                    uname = c.getValue();
                }
            }

            if (uname == null) {
                // 没有cookies，进入登录页面
                req.getRequestDispatcher("/admin/login.jsp").forward(req, resp);
            } else if ("admin".equals(uname)) {
                // 判断用户是否存在
                // 进入主页
//                req.getRequestDispatcher("/admin/success.jsp").forward(req, resp);
                resp.sendRedirect("/myservlet/admin/success.jsp");
            } else {
                // 进入登录页面
                req.getRequestDispatcher("/admin/login.jsp").forward(req, resp);
            }
        }else {
            // 进入登录页面
            req.getRequestDispatcher("/admin/login.jsp").forward(req, resp);
        }

    }
}
