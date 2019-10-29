package com.bjsxt.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 使用反射进行调度
 */
@WebServlet(name = "BaseServlet")
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //解决post表单的中文乱码问题
        //req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        // 调度
        String methodStr = req.getParameter("method");

        // this代表当前对象，即UserServlet
        Class clazz = this.getClass();
        try {
            Method method = clazz.getMethod(methodStr,HttpServletRequest.class,HttpServletResponse.class);
            // 因为Servlet单例，所以只能使用当前对象，不能新建对象
            method.invoke(this,req,resp );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
