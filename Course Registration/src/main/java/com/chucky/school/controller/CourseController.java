package com.chucky.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.chucky.school.domain.Course;
import com.chucky.school.domain.CreatedRecord;
import com.chucky.school.service.CourseService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CourseController {

  @Autowired
  CourseService courseService;

  @PostMapping("/sys-admin/courses")
  public ResponseEntity<?> createCourse(
      @RequestParam(value = "credits", required = true) long credits,
      @RequestParam(value = "courseName", required = true) String courseName,
      @RequestParam(value = "courseCode", required = true) String courseCode,
      @RequestParam(value = "courseDescription", required = true) String courseDescription,
      @RequestParam(value = "department", required = true) String department,
      @RequestParam(value = "createdBy", required = true) String createdBy) {
    return ResponseEntity.ok(courseService.createCourse(credits, courseName, courseCode, courseDescription,
        department,
        new CreatedRecord(createdBy)));
  }

  @GetMapping("/sys-admin/courses")
  public ResponseEntity<?> readAllCourses() {
    return ResponseEntity.ok(courseService.readAllCourses());
  }

  @GetMapping("/sys-admin/courses/{courseName}")
  public ResponseEntity<?> readCourse(@PathVariable(value = "courseName") String courseName) {
    return ResponseEntity.ok(courseService.readCourse(courseName));
  }

  @PutMapping("/sys-admin/courses/{id}")
  public ResponseEntity<?> updateCourse(@PathVariable(value = "id", required = true) long id,
      @RequestParam(value = "credits", required = true) long credits,
      @RequestParam(value = "courseName", required = true) String courseName,
      @RequestParam(value = "courseCode", required = true) String courseCode,
      @RequestParam(value = "courseDescription", required = true) String courseDescription,
      @RequestParam(value = "department", required = true) String department,
      @RequestParam(value = "createdBy", required = true) String createdBy) {
    return ResponseEntity.ok(courseService.updateCourse(id,
        new Course(credits, courseName, courseCode, courseDescription, department, new CreatedRecord(createdBy))));
  }

  @DeleteMapping("/sys-admin/courses/{id}")
  public ResponseEntity<?> deleteCourse(@RequestParam(value = "id", required = true) long id) {
    courseService.deleteCourse(id);
    return ResponseEntity.ok("Course deleted successfully");
  }

}
