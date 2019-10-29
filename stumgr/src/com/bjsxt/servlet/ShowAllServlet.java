package com.bjsxt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.entity.Student;
import com.bjsxt.service.StudentService;
import com.bjsxt.service.impl.StudentServiceImpl;
import com.bjsxt.util.PageBean;

public class ShowAllServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String indexStr = req.getParameter("index");
        int index = 1;
        if (indexStr != null && !"".equals(indexStr)) {
            index = Integer.parseInt(indexStr);
        }

        // 每页显示记录数
        String sizeStr = req.getParameter("size");
        int size = 5;
        if (sizeStr != null && !"".equals(sizeStr)) {
            size = Integer.parseInt(sizeStr);
        }

        // 接收查询条件
        String name = req.getParameter("name");
        String minScoreStr = req.getParameter("minScore");
        double minScore = 0;
        if (minScoreStr!=null&&!"".equals(minScoreStr)){
            minScore = Double.parseDouble(minScoreStr);
        }

        StudentService studentService = new StudentServiceImpl();
        PageBean<Student> pageBean = new PageBean<>();
        pageBean.setIndex(index);
        pageBean.setSize(size);

        // studentService.findAll(pageBean);
        studentService.findAll(pageBean,name,minScore);
        req.setAttribute("pageBean", pageBean);
        req.setAttribute("name", name);
        req.setAttribute("minScore", minScoreStr);

        req.getRequestDispatcher("/jsp/showAll.jsp").forward(req, resp);
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

}
