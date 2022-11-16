package com.mmit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mmit.di.Teacher;
import com.mmit.di.University;



@Configuration
public class Config3 {
	@Bean
	public Teacher teacheBean() {
		return new Teacher();
	}
	
	@Bean
	public University universityBean() {
		var uni = new University(teacheBean());
		return uni;
	}
	
//	@Bean
//	public University university() {
//		var uni = new University();
//		uni.setTeacher(teacheBean());
//		
//		return uni;
//	}
	@Bean
	public University university() {
		return new University();
	}
}
