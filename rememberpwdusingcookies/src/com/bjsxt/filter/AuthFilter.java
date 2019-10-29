package com.bjsxt.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
       // 判断是否是登录或注册操作 login.jsp   register.jsp   login register
       HttpServletRequest request = (HttpServletRequest)req;
       String url = request.getRequestURL().toString();
       int contains1 = url.indexOf("login.jsp");
       int contains2 = url.indexOf("register.jsp");
       int contains5 = url.indexOf("index.jsp");
       String param = request.getQueryString();
       if (param!=null){
           int contains3 = param.indexOf("login");
           int contains4 = param.indexOf("register");
           if (contains1>=0 || contains2>=0 || contains3>=0 || contains4>=0 || contains5>=0){
               // 不执行该过滤器
               chain.doFilter(req, resp);
               return;
           }
       }else {
           if (contains1>=0 || contains2 >= 0 || contains5>=0){
               chain.doFilter(req, resp);
               return;
           }
       }

       // 判断session中的userId是否为空，如果为空则转到login.jsp
        Object userIdObj = request.getSession().getAttribute("user");
       if (userIdObj == null){
           // 转到登录界面
           ((HttpServletResponse)resp).sendRedirect("/remember/login.jsp");
           return;
       }
        chain.doFilter(req, resp);

    }
}
