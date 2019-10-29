package com.bjsxt.servlet;

import com.bjsxt.entity.Duty;
import com.bjsxt.entity.Employee;
import com.bjsxt.entity.ExpenseItem;
import com.bjsxt.service.DutyService;
import com.bjsxt.service.impl.DutyServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "DutyServlet")
public class DutyServlet extends BaseServlet {

    private DutyService dutyService = new DutyServiceImpl();

    /**
     * 签到
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void signin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取empId
        Object empIdObj = req.getSession().getAttribute("emp");
        Employee emp = null;
        String empId = null;
        if (empIdObj!=null){
            emp = (Employee)empIdObj;
            empId=emp.getEmpId();
        }
        // 调用业务层
        String  res = dutyService.signin(empId);

        resp.getWriter().println(res);
    }

    /**
     * 签退
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void signout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取empId
        Object empIdObj = req.getSession().getAttribute("emp");
        Employee emp = null;
        String empId = null;
        if (empIdObj!=null){
            emp = (Employee)empIdObj;
            empId=emp.getEmpId();
        }
        // 调用业务层
        String res = dutyService.signout(empId);
        resp.getWriter().println(res);
    }
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Duty> dutyList = getConditionData(req);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        String jsonStr = gson.toJson(dutyList);
        resp.getWriter().println(jsonStr);

    }

    private List getConditionData(HttpServletRequest req){
        String empId = req.getParameter("empId");
        String deptNoStr = req.getParameter("deptNo");
        int deptNo = 0;
        if (deptNoStr!=null&&!"".equals(deptNoStr)){
            deptNo = Integer.parseInt(deptNoStr);
        }
        String dtDateStr = req.getParameter("dtDate");
        java.sql.Date dtDate = null;
        if (dtDateStr!=null && !"".equals(dtDateStr)){
            dtDate = java.sql.Date.valueOf(dtDateStr);
        }

        List<Duty> dutyList = dutyService.list(empId, deptNo, dtDate);
        return dutyList;
    }

    public void exportXls(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 获取数据
        List<Duty> dutyList = getConditionData(req);
        createExcel(dutyList, resp);
    }

    /**
     *
     * @param list 数据
     * @param resp  响应
     * @return worlnook 工作簿
     */
    private static void createExcel(List<Duty> list,HttpServletResponse resp) {
        // 创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个sheet 设置sheet名
        HSSFSheet sheet = workbook.createSheet("尚学堂考勤信息");


        // 创建1行6列单元格
        CellRangeAddress region = new CellRangeAddress(0, // first row
                0, // last row
                0, // first column
                5 // last column
        );
        // 添加合并单元格
        sheet.addMergedRegion(region);

        // 给创建的合并单元格添加数据
        HSSFRow hssfRow = sheet.createRow(0);
        HSSFCell headCell = hssfRow.createCell(0);

        headCell.setCellValue("考勤信息");


        // 设置单元格格式居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        headCell.setCellStyle(cellStyle);


        // 添加表头行
        hssfRow = sheet.createRow(1);
        // 添加表头内容
        headCell = hssfRow.createCell(0);
        headCell.setCellValue("用户名");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(1);
        headCell.setCellValue("真实姓名");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(2);
        headCell.setCellValue("所属部门");
        headCell.setCellStyle(cellStyle);


        headCell = hssfRow.createCell(3);
        headCell.setCellValue("出勤日期");
        headCell.setCellStyle(cellStyle);


        headCell = hssfRow.createCell(4);
        headCell.setCellValue("签到时间");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(5);
        headCell.setCellValue("签退时间");
        headCell.setCellStyle(cellStyle);

        // 添加数据内容
        for (int i = 0; i < list.size(); i++) {
            hssfRow = sheet.createRow((int) i + 2);
            Duty duty = list.get(i);

            // 创建单元格，并设置值
            HSSFCell cell = hssfRow.createCell(0);
            cell.setCellValue(duty.getEmpId());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(1);
            cell.setCellValue(duty.getEmp().getRealName());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(2);
            cell.setCellValue(duty.getEmp().getDept().getDeptName());
            cell.setCellStyle(cellStyle);

            // 创建单元格样式
         /*   HSSFCellStyle hssfCellStyleDate = workbook.createCellStyle();
            // 创建日期数据格式
            HSSFDataFormat df = workbook.createDataFormat();
            hssfCellStyleDate.setDataFormat(df.getFormat("yyyy-MM-dd"));*/

            cell = hssfRow.createCell(3);
            headCell.setCellValue(duty.getDtDate());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(4);
            cell.setCellValue(duty.getSigninTime());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(5);
            cell.setCellValue(duty.getSignoutTime());
            cell.setCellStyle(cellStyle);
        }


        OutputStream out = null;
        try {
            out = resp.getOutputStream();
            //response.reset();
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-disposition", "attachment; fileName=" + new String(("duty.xls").getBytes(), "iso8859-1"));
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
