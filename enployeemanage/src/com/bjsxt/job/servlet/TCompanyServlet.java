package com.bjsxt.job.servlet;

import com.bjsxt.job.entity.TCompany;
import com.bjsxt.job.service.impl.TCompanyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TCompanyServlet",urlPatterns = "/servlet/TCompanyServlet")
public class TCompanyServlet extends BaseServlet{
    public void findCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<TCompany> companyList = new TCompanyServiceImpl().findCompany();

        request.setAttribute("companyList", companyList);
        request.getRequestDispatcher("/job/publish.jsp").forward(request, response);

    }
}
