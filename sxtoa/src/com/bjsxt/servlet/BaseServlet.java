package com.bjsxt.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 这个Servlet是用来做父类的，不让通过地址直接访问，所以不进行web.xml配置,还可以设置为abstract类
 */
    public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PrinterWriter out = response.getWriter();
        //解决POST表单的中文乱码问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //接收method属性的值
        String methodName = request.getParameter("method");


        //通过反射调用方法
        try {
            //得到当前对象的类对象
            Class clazz = this.getClass();//this ???  UserServlet
            //通过反射创建对象 Servlet是单实例多线程程序
            //Object obj  = clazz.newInstance();
            //获取方法
            Method method = clazz.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            //通过反射调用方法
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
