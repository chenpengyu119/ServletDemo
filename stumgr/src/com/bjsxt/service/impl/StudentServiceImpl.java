package com.bjsxt.service.impl;

import java.util.List;

import com.bjsxt.dao.StudentDao;
import com.bjsxt.dao.impl.StudentDaoImpl;
import com.bjsxt.entity.Student;
import com.bjsxt.service.StudentService;
import com.bjsxt.util.PageBean;

public class StudentServiceImpl implements StudentService {

	private StudentDao stuDao = new StudentDaoImpl();	

	@Override
	public List<Student> findAll(PageBean pageBean) {

		// 查询总记录数
		// int totalCount = stuDao.findAll().size();
		int totalCount = stuDao.findCount();
		pageBean.setTotalCount(totalCount);

		// 获取数据
		List<Student> list = stuDao.findPage(pageBean);
		pageBean.setList(list);

		return this.stuDao.findAll();
	}

	@Override
	public void findAll(PageBean<Student> pageBean, String name, double minScore) {

		// 查询总记录数
		// int totalCount = stuDao.findAll().size();
		int totalCount = stuDao.findCount(name,minScore);
		pageBean.setTotalCount(totalCount);

		// 获取数据
		List<Student> list = stuDao.findPage(pageBean,name,minScore);
		pageBean.setList(list);
	}
}
