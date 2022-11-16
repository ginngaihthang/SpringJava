package com.mmit.di;

import org.springframework.beans.factory.annotation.Autowired;

public class University {

	@Autowired
	private Teacher teacher;
	
	public University() {
		
	}
	public University(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public void show() {
		System.out.println("University's show method");
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	
}
