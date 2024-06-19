package com.chucky.school.service;

import java.util.List;

import com.chucky.school.domain.Course;
import com.chucky.school.domain.AuditData;

public interface ICourseService {
  public Course createCourse(long credits, String courseName, String courseCode, String courseDescription,
      String department, AuditData createdRecord, String sessionTitle, String sessionType);

  public Course readCourse(String courseName);

  public List<Course> readAllCourses();

  public Course updateCourse(long id, Course course);

  public void deleteCourse(long id);

}
