package com.bjsxt.job.servlet;

import com.bjsxt.job.entity.Job;
import com.bjsxt.job.service.JobService;
import com.bjsxt.job.service.impl.JobServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.DatagramPacket;
import java.util.List;

@WebServlet(urlPatterns = "/servlet/JobServlet")
public class JobServlet extends BaseServlet {

    public void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取数据
        String pname = request.getParameter("job_condition");
        JobService jobService = new JobServiceImpl();
        List<Job> jobList = jobService.find(pname);

        // 响应数据
        request.setAttribute("jobList", jobList);
        request.setAttribute("pname", pname);
        request.getRequestDispatcher("/job/show.jsp").forward(request, response);
    }


}
