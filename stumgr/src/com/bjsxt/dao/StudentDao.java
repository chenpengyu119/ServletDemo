package com.bjsxt.dao;

import java.util.List;

import com.bjsxt.entity.Student;
import com.bjsxt.util.PageBean;

public interface StudentDao {
	/**
	 * 查询所有学生
	 * @return
	 */
	public List<Student> findAll();

	public List<Student> findPage(PageBean pageBean);

	public int findCount();

    public int findCount(String name, double minScore);

	public List<Student> findPage(PageBean<Student> pageBean, String name, double minScore);
}
