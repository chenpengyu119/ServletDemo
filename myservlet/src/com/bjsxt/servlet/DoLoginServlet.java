package com.bjsxt.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class DoLoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 设置请求编码格式
        request.setCharacterEncoding("utf-8");
        //  设置响应编码格式
        response.setContentType("text/html;charset=utf-8");


        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");

/*        // 获取cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                System.out.println(c.getName());
                System.out.println(c.getValue());
            }
        }*/

        // get方式格式设置
//        uname = new String(uname.getBytes("iso8859-1"),"utf-8");
//        pwd = new String(pwd.getBytes("iso8859-1"),"utf-8");

        // 表单格式验证
        if (uname == null || "".equals(uname)){
            // 设置返回值
            request.setAttribute("unameerr", "用户名为空（jsp）");
            // 跳转回源页面
            request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
            // 结束Servlet
            return;
        }

        if (pwd == null || pwd.length() <= 0){
            request.setAttribute("pwderr", "密码为空(jsp)");
            request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
            return;
        }

        // 页面跳转
            if (uname!=null && pwd != null &&uname.equals("admin") && pwd.equals("toor")) {

                // 设置cookies
                String name = "uid";
                String value = uname;
                Cookie c = new Cookie(name, value);
                // 三天免登录
                c.setMaxAge(3*3600*24);
                c.setPath("/myservlet/servlet/ck");
                response.addCookie(c);

                // 设置session
                HttpSession hs = request.getSession();
                // 将用户信息添加到Session
                hs.setAttribute("uname", uname);
                hs.setAttribute("pwd", pwd);

                request.getRequestDispatcher("/admin/success.jsp").forward(request, response);
                //response.sendRedirect("/myservlet/admin/login.jsp");
        }else {
            request.setAttribute("err", "用户名或密码不正确(jsp)");
          //  request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
            response.sendRedirect("/myservlet/admin/false.html");
        }

    }
}
