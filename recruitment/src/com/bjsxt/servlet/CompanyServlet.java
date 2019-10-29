package com.bjsxt.servlet;

import com.bjsxt.entity.Company;
import com.bjsxt.service.CompanyService;
import com.bjsxt.service.impl.CompanyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CompanyServlet extends BaseServlet {

    private CompanyService companyService = new CompanyServiceImpl();
    /**
     * 查询公司
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void toPublish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Company> companyList = companyService.findList();

        req.setAttribute("companyList", companyList);
        req.getRequestDispatcher("/publish.jsp").forward(req, resp);

    }
}
