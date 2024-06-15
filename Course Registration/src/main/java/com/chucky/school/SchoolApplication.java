package com.chucky.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.chucky.school.service.CourseService;

@SpringBootApplication
public class SchoolApplication implements CommandLineRunner {

	@Autowired
	private CourseService courseService;

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// CourseService courseService = courseService(40L, "Math", "MATH101",
		// "Introduction to Math", "Mathematics",
		// "Science");

	}
}
