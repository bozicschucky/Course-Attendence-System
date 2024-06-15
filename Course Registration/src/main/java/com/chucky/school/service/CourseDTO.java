package com.chucky.school.service;

import com.chucky.school.domain.CreatedRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseDTO {

  @Autowired
  private CourseService courseService;

  public CourseService createCourseService(long credits, String courseName, String courseCode,
      String courseDescription,
      String department, CreatedRecord createdBy) {
    courseService.createCourse(credits, courseName, courseCode, courseDescription, department, createdBy);

    return courseService;
  }

}
