package com.chucky.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.chucky.school.domain.Course;
import com.chucky.school.domain.CreatedRecord;
import com.chucky.school.service.CourseDTO;
import com.chucky.school.service.CourseService;

@SpringBootApplication
public class SchoolApplication implements CommandLineRunner {

	@Autowired
	private CourseDTO courseDTO;

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Create a course using CourseDTO
		CourseService courseService = courseDTO.createCourseService(3, "Math", "MATH101", "Introduction to Math",
				"Mathematics",
				new CreatedRecord("admin"));

		courseService.readCourse("Math");

		courseService.readAllCourses();

		// Update a course
		courseService.updateCourse(1,
				new Course(3, "Math", "MATH101", "Introduction to Math", "Mathematics", new CreatedRecord("admin")));

		// Delete a course
		// courseService.deleteCourse(1);

	}
}
