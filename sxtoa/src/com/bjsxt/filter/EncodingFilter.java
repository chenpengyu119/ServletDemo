package com.bjsxt.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    String encoding = null;
    /**
     * 初始化  只执行一次
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //读取web.xml，获取初始化参数
         encoding = filterConfig.getInitParameter("encoding");
         if(encoding == null){
             encoding = "utf-8";
         }
    }

    /**
     *  相当于Servlet的service()，过滤范围内每次请求都经过

     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //1.请求到达目标资源之前的预处理操作
        //request.setCharacterEncoding("utf-8");
        request.setCharacterEncoding(encoding);
        //2.调用下一个过滤器或者目标资源
        chain.doFilter(request,response);
        //3.响应离开目标资源后的后出来操作

    }

    /**
     * 扫尾操作，执行一次
     */
    @Override
    public void destroy() {

    }
}
