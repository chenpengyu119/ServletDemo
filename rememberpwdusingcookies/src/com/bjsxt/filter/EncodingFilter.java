package com.bjsxt.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * @author pengyu
 */
@WebFilter(filterName = "EncodingFilter"/*,urlPatterns = "/servlet/*",initParams = {
        @WebInitParam(name = "encoding", value = "utf-8")
}*/)
public class EncodingFilter implements Filter {
    /**
     *编码格式
     */
    String encoding;

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        // 预处理
        req.setCharacterEncoding(encoding);

        // 调用其它目标资源
        chain.doFilter(req, resp);

        //后处理
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
        if (encoding == null){
            encoding = "utf-8";
        }
    }

}
