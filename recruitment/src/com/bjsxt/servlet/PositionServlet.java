package com.bjsxt.servlet;

import com.bjsxt.entity.Company;
import com.bjsxt.entity.Position;
import com.bjsxt.service.PositionService;
import com.bjsxt.service.impl.PositionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class PositionServlet extends BaseServlet {

    private PositionService positionService = new PositionServiceImpl();
    /**
     * 条件查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findByPname(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 接收数据
        String pname = req.getParameter("pname");

        List<Position> posList = positionService.findByPname(pname);

        // 返回数据
        req.setAttribute("posList",posList );
        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }

    /**
     * 发布职位信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void publish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取数据
        String pname = req.getParameter("pname");
        String minsalStr = req.getParameter("minsal");
        double minsal = 0;
        if (minsalStr!=null&&!"".equals(minsalStr)){
            minsal = Double.parseDouble(minsalStr);
        }

        String maxsalStr = req.getParameter("maxsal");
        double maxsal = 0;
        if (maxsalStr!=null&&!"".equals(maxsalStr)){
            maxsal = Double.parseDouble(maxsalStr);
        }

        String cidStr = req.getParameter("company");
        int cid = 0;
        if (cidStr!=null&&!"".equals(cidStr)){
            cid = Integer.parseInt(cidStr);
        }

         Position pos = new Position(0, pname, minsal, maxsal, null, cid,null );
         int res = positionService.add(pos);
         if (res > 0){
             resp.sendRedirect("/servlet/PositionServlet?method=findByPname");
         }else {
             req.setAttribute("error", "发布失败");
         }

    }

    public void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收数据
        String pidStr = req.getParameter("pid");
        int pid = 0;
        if (pidStr!=null&&!"".equals(pidStr)){
            pid = Integer.parseInt(pidStr);
        }

        int res = positionService.del(pid);
        if (res>0){
            resp.sendRedirect("/servlet/PositionServlet?method=findByPname");
        }else {
            req.setAttribute("error", "删除失败");
            req.getRequestDispatcher("/index.jsp");
        }

    }


}
