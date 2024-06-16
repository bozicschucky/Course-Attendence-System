package com.chucky.school.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chucky.school.domain.Course;
import com.chucky.school.domain.AuditData;
import com.chucky.school.service.CourseService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class CourseControllerTest {

  @Mock
  private CourseService courseService;

  @InjectMocks
  private CourseController courseController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCreateCourse() {
    long credits = 3;
    String courseName = "Math";
    String courseCode = "MATH101";
    String courseDescription = "Introduction to Mathematics";
    String department = "Mathematics Department";
    String createdBy = "Admin";
    AuditData createdRecord = new AuditData(createdBy);
    Course course = new Course(credits, courseName, courseCode, courseDescription, department, createdRecord);

    when(courseService.createCourse(anyLong(), anyString(), anyString(), anyString(), anyString(),
        any(AuditData.class)))
        .thenReturn(course);

    ResponseEntity<?> response = courseController.createCourse(credits, courseName, courseCode, courseDescription,
        department, createdBy);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(Map.of("message", "Course created successfully", "course", course), response.getBody());
    verify(courseService, times(1)).createCourse(anyLong(), anyString(), anyString(), anyString(), anyString(),
        any(AuditData.class));
  }

  @Test
  public void testReadAllCourses() {
    List<Course> courses = new ArrayList<>();
    courses.add(new Course(3, "Math", "MATH101", "Introduction to Mathematics", "Mathematics Department",
        new AuditData("Admin")));
    courses.add(new Course(4, "Science", "SCI101", "Introduction to Science", "Science Department",
        new AuditData("Admin")));

    when(courseService.readAllCourses()).thenReturn(courses);

    ResponseEntity<?> response = courseController.readAllCourses();

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(courses, response.getBody());
    verify(courseService, times(1)).readAllCourses();
  }

  @Test
  public void testReadCourse() {
    String courseName = "Math";
    Course course = new Course(3, courseName, "MATH101", "Introduction to Mathematics", "Mathematics Department",
        new AuditData("Admin"));

    when(courseService.readCourse(courseName)).thenReturn(course);

    ResponseEntity<?> response = courseController.readCourse(courseName);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(course, response.getBody());
    verify(courseService, times(1)).readCourse(courseName);
  }

  @Test
  public void testUpdateCourse() {
    long id = 1;
    long credits = 3;
    String courseName = "Math";
    String courseCode = "MATH101";
    String courseDescription = "Introduction to Mathematics";
    String department = "Mathematics Department";
    String createdBy = "Admin";
    AuditData createdRecord = new AuditData(createdBy);
    Course updatedCourse = new Course(credits, courseName, courseCode, courseDescription, department, createdRecord);

    when(courseService.updateCourse(anyLong(), any(Course.class))).thenReturn(updatedCourse);

    ResponseEntity<?> response = courseController.updateCourse(id, credits, courseName, courseCode, courseDescription,
        department, createdBy);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(Map.of("message", "Course updated successfully", "course", updatedCourse), response.getBody());
    verify(courseService, times(1)).updateCourse(anyLong(), any(Course.class));
  }

  @Test
  public void testDeleteCourse() {
    long id = 1;

    ResponseEntity<?> response = courseController.deleteCourse(id);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(Map.of("id", id, "message", "Course deleted successfully"), response.getBody());
    verify(courseService, times(1)).deleteCourse(id);
  }
}