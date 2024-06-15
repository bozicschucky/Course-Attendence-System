package com.chucky.school.service;

import java.util.List;

import com.chucky.school.domain.Course;

public interface ICourseService {
  public void createCourse();

  public Course readCourse(String courseName);

  public List<Course> readAllCourses();

  public Course updateCourse(long id, Course course);

  public void deleteCourse(long id);

}
