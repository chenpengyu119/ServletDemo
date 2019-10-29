package com.bjsxt.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        //http://127.0.0.1:8080/myservlet2/servlet/UserServlet?method=login
        //http://127.0.0.1:8080/myservlet2/servlet/UserServlet?method=register
        //http://127.0.0.1:8080/myservlet2/admin/login.jsp
        //http://127.0.0.1:8080/myservlet2/admin/register.jsp
        String url = request.getRequestURL().toString();
        String qs = request.getQueryString(); //可能是null
        int n1 = url.indexOf("login.jsp");//>=0
        int n2 = url.indexOf("register.jsp");//>=0
        int n3=-1;
        int n4=-1;
        if(qs  != null){
            n3 = qs.indexOf("method=login");
            n4 = qs.indexOf("method=register");
        }

        if(n1>=0 || n2>=0 || n3>=0 || n4>=0){ //如果是login.jsp  register.jsp servlet/UserServlet?method=login  servlet/UserServlet?method=register中的任何一个
            //允许访问，直接放行
            chain.doFilter(request,response);
        }else{
            //需要先判断是否已经登录
            //1.请求到达目标资源之前的预处理程序
            HttpSession session = request.getSession();
  /*          User user = (User)session.getAttribute("user");
            if(user == null){
                response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
                return;
            }*/
            //2.调用下一个过滤器或者目标资料
            chain.doFilter(request,response);
            //3.响应离开目标资源后的后处理程序
        }


    }

    @Override
    public void destroy() {

    }
}
