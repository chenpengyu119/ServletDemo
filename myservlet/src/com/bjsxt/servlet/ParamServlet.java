package com.bjsxt.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ParamServlet extends HttpServlet {
    private String initParamStr;
    private String contextStr;

    @Override
    public void init() throws ServletException {
        ServletConfig initParam = this.getServletConfig();

        initParamStr = initParam.getInitParameter("encoding");
        if (initParamStr == null){
            initParamStr = "utf-8";
        }

        // 全局
        ServletContext context = this.getServletContext();
        contextStr = context.getInitParameter("jdbc_property");
        if (contextStr == null){
            contextStr = "mysql";
        }
    }

//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println(initParamStr);
//        System.out.println(contextStr);
//    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(initParamStr);
        System.out.println(contextStr);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
