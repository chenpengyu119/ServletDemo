package com.bjsxt.service.impl;

import java.util.List;

import com.bjsxt.dao.StudentDao;
import com.bjsxt.dao.impl.StudentDaoImpl;
import com.bjsxt.entity.Student;
import com.bjsxt.service.StudentService;

public class StudentServiceImpl implements StudentService{

private StudentDao stuDao = new StudentDaoImpl();
	
	
	@Override
	public List<Student> findAll() {
		return this.stuDao.findAll();
	}

	@Override
	public Student findById(int id) {
		
		return this.stuDao.findById(id);
	}

	@Override
	public int save(Student stu) {
		
		return this.stuDao.save(stu);
	}

}
