package com.mmit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mmit.beans.Person;
import com.mmit.beans.Teacher;
import com.mmit.beans.University;
import com.mmit.config.BeanConfig;
import com.mmit.config.Config2;

@SpringBootApplication
public class Demo1Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
		
		var cxt = new AnnotationConfigApplicationContext(BeanConfig.class);
		
		//var teacher = cxt.getBean("teacherBean", Teacher.class);
		//teacher.teach();
//		var university = cxt.getBean(University.class);
//		university.show();
		
//		var person1 =cxt.getBean(Person.class);
//		person1.setName("Jeon");
//		
//		var person2 = cxt.getBean(Person.class);
//		System.out.println("Person1's name: " + person1.getName());
//		System.out.println("Person2's name: " + person2.getName());
//		
//		person2.setName("JK");
//		System.out.println("--------");
//		System.out.println("Person1's name: " + person1.getName());
//		System.out.println("Person2's name: " + person2.getName());
//		
//		cxt.close();
	
	}

}
