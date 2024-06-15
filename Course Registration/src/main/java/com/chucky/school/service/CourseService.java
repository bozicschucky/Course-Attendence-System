package com.chucky.school.service;

import java.util.List;

import com.chucky.school.domain.Course;
import com.chucky.school.domain.CreatedRecord;
import com.chucky.school.repository.CourseRepository;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class CourseService implements ICourseService {

  @Autowired
  private CourseRepository courseRepository;

  public Course createCourse(long credits, String courseName, String courseCode, String courseDescription,
      String department,
      CreatedRecord createdRecord) {
    Course course = new Course(credits, courseName, courseCode, courseDescription, department, createdRecord);
    courseRepository.save(course);
    return  course;
  }

  public Course readCourse(String courseName) {
    Course course = courseRepository.findByCourseName(courseName);
    return course;
  }

  public List<Course> readAllCourses() {
    List<Course> courses = courseRepository.findAll();
    return courses;
  }

  public Course updateCourse(long id, Course course) {
    Course courseToUpdate = courseRepository.findById(id).get();
    courseToUpdate.setCourseName(course.getCourseName());
    courseToUpdate.setCourseCode(course.getCourseCode());
    courseToUpdate.setCourseDescription(course.getCourseDescription());
    courseToUpdate.setCredits(course.getCredits());
    courseToUpdate.setDepartment(course.getDepartment());
    courseRepository.save(courseToUpdate);
    return courseToUpdate;
  }

  public void deleteCourse(long id) {
    courseRepository.deleteById(id);
  }
}