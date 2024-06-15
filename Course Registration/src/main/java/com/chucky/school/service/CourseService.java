package com.chucky.school.service;

import java.util.List;

import com.chucky.school.domain.Course;
import com.chucky.school.domain.CreatedRecord;
import com.chucky.school.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService implements ICourseService {

  @Autowired
  private CourseRepository courseRepository;

  private long credits;
  private String courseName;
  private String courseCode;
  private String courseDescription;
  private String department;
  private CreatedRecord createdRecord;

  public CourseService() {
  }

  public CourseService(long credits, String courseName, String courseCode, String courseDescription, String department,
      CreatedRecord createdRecord) {
    this.credits = credits;
    this.courseName = courseName;
    this.courseCode = courseCode;
    this.courseDescription = courseDescription;
    this.department = department;
    this.createdRecord = createdRecord;
  }

  public void createCourse() {
    Course course = new Course(credits, courseName, courseCode, courseDescription, department, createdRecord);
    courseRepository.save(course);

  }

  public Course readCourse(String courseName) {
    Course course = courseRepository.findByCourseName(courseName);
    System.out.println(course);
    return course;
  }

  public List<Course> readAllCourses() {
    List<Course> courses = courseRepository.findAll();
    System.out.println(courses);
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
    System.out.println("Course deleted successfully");
  }
}
