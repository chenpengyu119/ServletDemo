package com.bjsxt.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.bjsxt.entity.Student;
import com.bjsxt.service.StudentService;
import com.bjsxt.service.impl.StudentServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class AddServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1.创建工厂
		FileItemFactory itemFactory = new DiskFileItemFactory();
		// 2.创建文件上传组件对象
		ServletFileUpload upload = new ServletFileUpload(itemFactory);
		// 限制文件大小
		// 总文件大小
		upload.setSizeMax(96*1024);;
		// 单个文件大小
		upload.setFileSizeMax(16*1024);
		// 解决文件名乱码
		upload.setHeaderEncoding("utf-8");
		// 3. 上传并封装表单项
		List<FileItem> itemList = null;
		try {
			 itemList = upload.parseRequest(request);
		} catch (FileUploadException e) {
			request.setAttribute("mess", "单个文件不能超过16KB，总文件不能超过96KB!");
			request.getRequestDispatcher("/add.jsp").forward(request, response);
			return;
		}
		// 4.遍历集合并处理
		String name = null;
		int age = 0;
		double score = 0;
		String fileName = null;
		String photoName = null;
		String fieldName = null;
		String contentType = null;

		for (FileItem item:itemList){
			// 表单name
			fieldName = item.getFieldName();
			if (item.isFormField()){
				// 普通表单项
				// 判断是哪个表单项
				if ("name".equals(fieldName)){
					name = item.getString("utf-8");
				}else if ("age".equals(fieldName)){
					age = Integer.parseInt(item.getString());
				}else if ("score".equals(fieldName)){
					score = Double.parseDouble(item.getString());
				}
			}else {
				// 文件
				if ("photo".equals(fieldName)){
					// 文件路径
					String pathname = "D:/upload";
					File path = new File(pathname);
					// 文件路径不存在
					if (!path.exists()){
						path.mkdir();
					}
					photoName = item.getName();
					UUID uuid = UUID.randomUUID();
					fileName = uuid.toString();
					// 后缀
					String suffix = photoName.substring(photoName.lastIndexOf('.'));
					fileName += suffix;
					// 限制文件类型 通过MIME判断
					contentType = item.getContentType();
					if (!"image/jpeg".equals(contentType)&&!"image/gif".equals(contentType)){
						request.setAttribute("mess", "只支持jpg和gif格式");
						request.getRequestDispatcher("/add.jsp").forward(request, response);
						return;
					}

					try {
						item.write(new File(path, fileName));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}
		StudentService studentService = new StudentServiceImpl();
		// String name, int age, double score, String realName, String photoName, String photoType
		Student student = new Student(name, age, score, photoName, fileName, contentType);
		int n= studentService.save(student);
		//页面跳转
		if(n!=0){
			//重定向:/后面要跟上下文路径  /stumgr   /stumgr2
			response.sendRedirect(request.getContextPath()+"/servlet/ShowAllServlet");
		}else{
			request.setAttribute("mess", "添加失败!");
			request.getRequestDispatcher("/add.jsp").forward(request, response);
		}

	}

}
