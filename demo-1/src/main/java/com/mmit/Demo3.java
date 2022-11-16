package com.mmit;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mmit.config.Config3;
import com.mmit.di.University;

public class Demo3 {
	
	public static void main(String[] args) {
		var cxt = new AnnotationConfigApplicationContext(Config3.class);
		
//		var univeristy = cxt.getBean("universityBean", University.class);
//		
//		univeristy.show();
		var university = cxt.getBean("university", University.class);
		
		university.show();
	}
}
