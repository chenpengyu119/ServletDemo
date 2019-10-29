package com.bjsxt.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Date;


/**
 * 记录访问记录(ip url time)
 */
@WebListener()
public class LogListener  implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        // 新建请求时触发
        HttpServletRequest request = (HttpServletRequest)sre.getServletRequest();
        String ip = request.getRemoteHost();
        String url = request.getRequestURI();
        String param = request.getQueryString();
        String time = new Date().toLocaleString();

        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileOutputStream("bjsxt.log",true));
            if (param!=null){
                out.println("时间："+time+",ip:"+ip+",URL:"+url+"?"+param);
            }else {
                out.println("时间："+time+",ip:"+ip+",URL:"+url);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (out!=null){
                out.close();
            }
        }
    }

}
