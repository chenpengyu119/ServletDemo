package com.yu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CodeServlet",urlPatterns = "/servlet/CodeServlet")
public class CodeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取请求数据
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String code = req.getParameter("code");
        System.out.println("code："+code);

        Map<String,String> map = new HashMap<String,String>();
        map.put("010","北京,北京");
        map.put("0351","山西,太原");
        map.put("0311","河北,石家庄");
        map.put("0451","黑龙江,哈尔滨");
        map.put("024","辽宁,沈阳");
        String result = map.get(code);
        if (result==null){
            resp.getWriter().println("error,error");
        }else {
            resp.getWriter().println(result);
        }
    }
}
