package com.bjsxt.service;

import java.util.List;

import com.bjsxt.entity.Student;
import com.bjsxt.util.PageBean;

public interface StudentService {

	/**
	 * 查询所有学生
	 * @return
	 */
	public List<Student> findAll(PageBean pageBean);


    public void findAll(PageBean<Student> pageBean, String name, double minScore);
}
