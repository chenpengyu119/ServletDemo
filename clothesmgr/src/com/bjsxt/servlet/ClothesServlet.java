package com.bjsxt.servlet;

import com.bjsxt.entity.Cloth;
import com.bjsxt.service.ClothesService;
import com.bjsxt.service.impl.ClothesServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ClothesServlet")
public class ClothesServlet extends BaseServlet {

    private ClothesService clothesService = new ClothesServiceImpl();

    public void findByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 接收数据
        String cname = req.getParameter("cname");

        List<Cloth> clothList = clothesService.findByName(cname);

        req.setAttribute("clothesList", clothList);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }


}
