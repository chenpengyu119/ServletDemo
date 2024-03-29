package com.bjsxt.entity;

import java.sql.Date;

/**
 * 学生实体类
 * @author Administrator
 *
 */
public class Student {

	private int id;
	private String name;
	private int age;
	private double score;
	private String realName;
	private String photoName;
	private String photoType;
	
	public Student() {
	}
	public Student(int id, String name, int age, double score) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.score = score;
	}

	public Student(String name, int age, double score, String realName, String photoName, String photoType) {
		this.name = name;
		this.age = age;
		this.score = score;
		this.realName = realName;
		this.photoName = photoName;
		this.photoType = photoType;
	}

	public Student(String name, int age, double score) {
		super();
		this.name = name;
		this.age = age;
		this.score = score;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoType() {
		return photoType;
	}

	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}

	
}
