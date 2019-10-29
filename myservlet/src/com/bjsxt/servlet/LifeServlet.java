package com.bjsxt.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LifeServlet")
public class LifeServlet extends HttpServlet {

    public LifeServlet(){
        System.out.println("-------------------实例化---------------------------");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("-----------------------初始化-----------------------------");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("----------------------service服务----------------------");
    }

    @Override
    public void destroy() {
        System.out.println("------------------------销毁---------------------------");
    }
}
