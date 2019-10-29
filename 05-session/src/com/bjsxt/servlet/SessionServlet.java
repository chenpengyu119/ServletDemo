package com.bjsxt.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author pengyu
 */
@WebServlet(name = "SessionServlet",urlPatterns = {"/servlet/ss"})
public class SessionServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        // 如果没有session就会直接创建session，如果有则获取session
        HttpSession hs = req.getSession();
        // 默认单位：秒
        hs.setMaxInactiveInterval(5);
        hs.invalidate();
        System.out.println(hs.getId());
    }
}
