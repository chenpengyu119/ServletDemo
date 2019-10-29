package com.bjsxt.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.SplittableRandom;

@WebServlet(name = "GetInfoFromReqRespServlet",urlPatterns = {"/servlet/GetInfoFromReqRespServlet"})
public class GetInfoFromReqRespServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. 获取req中的信息
        // 1.1请求头
        String userAgent = "User-Agent:"+req.getHeader("User-Agent");
        // 上一个页面
        String referer = "RefererReferer:"+req.getHeader("Referer");

        // 1.2. 表单数据
        String uname = "uname:"+req.getParameter("uname");
        // 一组数据
        String hobby = "hobby:"+Arrays.toString(req.getParameterValues("hobby")) ;


        // 1.3 服务器和客户端地址
        // 客户端
        String remoteHost = "RemoteHost:"+req.getRemoteHost();
        String remoteAddr = "RemoteAddr:"+req.getRemoteAddr();
        String  remotePort = "RemotePort:"+String.valueOf(req.getRemotePort());

        // 服务器端
        String localHost = "LocalName:"+req.getLocalName();
        String localAddr = "LocalAddr:"+req.getLocalAddr();
        String localPort = "LocalPort:"+req.getLocalPort();

        // 1.4 URL地址
        String scheme = "Scheme:"+req.getScheme();
        String serverName = "ServerName:"+req.getServerName();
        String servetPort = "ServerPort:"+req.getServerPort();
        String contextPath = "contextPath:"+req.getContextPath();
        String servletPath = "ServletPath:"+req.getServletPath();

        String reqURL = "ReqURL:"+req.getRequestURL();
        String reqURI = "ReqURI:"+req.getRequestURI();
        String queryString = "QueryString:"+req.getQueryString();

        // 1.5 Servlet其他对象
        Cookie [] cookies = req.getCookies();
        HttpSession session = req.getSession();
        ServletContext context = req.getServletContext();

        // 2.关于响应
        resp.setHeader("key1", "v1");
        resp.setHeader("key1", "v2");

        resp.addHeader("key1", "v3");
        testPrint(userAgent,referer,uname,hobby,remoteHost,
                remoteAddr,remotePort,localHost,localAddr,localPort,
                scheme,serverName,servetPort,contextPath,servletPath,
                reqURL,reqURI,queryString,cookies,session,context);
    }

    private void testPrint(Object ...objects){
        for (Object o: objects){
            if (o instanceof Cookie[]){
                System.out.println(Arrays.toString((Cookie[])o));
            }else {
                System.out.println(o);
            }
        }
    }
}
