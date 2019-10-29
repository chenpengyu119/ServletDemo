package com.bjsxt.servlet;

import com.bjsxt.entity.Student;
import com.bjsxt.service.StudentService;
import com.bjsxt.service.impl.StudentServiceImpl;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@WebServlet(name = "DownServlet",urlPatterns = "/servlet/DownServlet")
public class DownServlet extends HttpServlet {
    private StudentService studentService = new StudentServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // 接收数据
        String idStr = request.getParameter("stuId");
        int id = 0;
        if (idStr != null && !"".equals(idStr)) {
            id = Integer.parseInt(idStr);
        }
        Student stu = studentService.findById(id);

        File file = new File(new File("D:/upload"), stu.getPhotoName());
        // 霓凰郡主.jpg
        String realName = stu.getRealName();
        String userAgent = request.getHeader("User-Agent").toLowerCase();

        if(userAgent.indexOf("msie")>=0){
            // ie解决乱码的方案
            realName = URLEncoder.encode(realName, "utf-8");
        }else{
            // 其他浏览器解决乱码的方案
            realName = new String(realName.getBytes("utf-8"),"iso-8859-1");
        }


        //文件长度
        response.setContentLength((int) file.length());
        // 文件类型 MIME类型
        response.setContentType(stu.getPhotoType());
        response.setHeader("Content-disposition", "attachment;filename="+realName);


        InputStream is = new FileInputStream(file);
        OutputStream os = response.getOutputStream();
        // 使用Apache的工具包实现文件复制
        IOUtils.copy(is, os);
        is.close();
        os.close();

    }
}
