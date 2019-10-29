package com.bjsxt.servlet;

import com.bjsxt.dao.DetailDao;
import com.bjsxt.dao.impl.DetailDaoImpl;
import com.bjsxt.entity.Cloth;
import com.bjsxt.entity.Detail;
import com.bjsxt.service.DetailService;
import com.bjsxt.service.impl.DetailServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DetailServlet")
public class DetailServlet extends BaseServlet {
    private DetailService detailService = new DetailServiceImpl();

    public void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收数据


        String  cidStr = req.getParameter("cid");
        int cid = 0;
        if (cidStr!=null&&!"".equals(cidStr)){
            cid = Integer.parseInt(cidStr);
        }else {
            throw new RuntimeException("没有接受到数据");
        }

        String cname = req.getParameter("cname");
        String ebrand = req.getParameter("ebrand");
        String cgender = req.getParameter("cgender");

        Detail detail = detailService.findByCid(cid);
        Cloth cloth = new Cloth();
        cloth.setCname(cname);
        cloth.setEbrand(ebrand);
        cloth.setCgender(cgender);
        detail.setCloth(cloth);

        req.setAttribute("detail",detail);
        req.getRequestDispatcher("/detail.jsp").forward(req, resp);

    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 接收数据
        String cname = req.getParameter("cname");
        String ebrand = req.getParameter("ebrand");

        String cgender = req.getParameter("cgender");

        String dsizeStr = req.getParameter("dsize");
        float dsize = 0;
        if (dsizeStr!=null&&!"".equals(dsizeStr)){
            dsize = Float.parseFloat(dsizeStr);
        }
        String dcolor = req.getParameter("dcolor");

        String dpriceStr = req.getParameter("dprice");
        float dprice = 0;
        if (dpriceStr!=null&&!"".equals(dpriceStr)){
            dprice = Float.parseFloat(dpriceStr);
        }
        Cloth cloth = new Cloth(0, cname, ebrand, cgender);
        Detail detail = new Detail(0, dcolor, dprice, dsize, 0, cloth);

        try {
            detailService.add(detail);
        }catch (Exception e){
            e.printStackTrace();
        }

        resp.sendRedirect("/clothesmgr/success.jsp");

    }

}
