package com.chucky.school.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.chucky.school.domain.Course;
import com.chucky.school.domain.CreatedRecord;
import com.chucky.school.repository.CourseRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CourseServiceTest {

  @Mock
  private CourseRepository courseRepository;

  @InjectMocks
  private CourseService courseService;

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
    CreatedRecord createdRecord = new CreatedRecord("Admin");
    Course course = new Course(credits, courseName, courseCode, courseDescription, department, createdRecord);

    when(courseRepository.save(any(Course.class))).thenReturn(course);
    Course createdCourse = courseService.createCourse(credits, courseName, courseCode, courseDescription,
        department, createdRecord);

    assertNotNull(createdCourse);
    assertEquals(courseName, createdCourse.getCourseName());
    verify(courseRepository, times(1)).save(any(Course.class));
  }

  @Test
  public void testReadCourse() {
    String courseName = "Math";
    Course course = new Course(3, courseName, "MATH101", "Introduction to Mathematics", "Mathematics Department",
        new CreatedRecord("Admin"));

    when(courseRepository.findByCourseName(courseName)).thenReturn(course);
    Course retrievedCourse = courseService.readCourse(courseName);

    assertNotNull(retrievedCourse);
    assertEquals(courseName, retrievedCourse.getCourseName());
    verify(courseRepository, times(1)).findByCourseName(courseName);
  }

  @Test
  public void testReadAllCourses() {
    List<Course> courses = new ArrayList<>();
    courses.add(new Course(3, "Math", "MATH101", "Introduction to Mathematics", "Mathematics Department",
        new CreatedRecord("Admin")));
    courses.add(new Course(4, "Science", "SCI101", "Introduction to Science", "Science Department",
        new CreatedRecord("Admin")));

    when(courseRepository.findAll()).thenReturn(courses);
    List<Course> retrievedCourses = courseService.readAllCourses();

    assertNotNull(retrievedCourses);
    assertEquals(courses.size(), retrievedCourses.size());
    verify(courseRepository, times(1)).findAll();
  }

  @Test
  public void testUpdateCourse() {
    long id = 1;
    Course existingCourse = new Course(3, "Math", "MATH101", "Introduction to Mathematics",
        "Mathematics Department", new CreatedRecord("Admin"));
    Course updatedCourse = new Course(3, "Mathematics", "MATH101", "Introduction to Mathematics",
        "Mathematics Department", new CreatedRecord("Admin"));

    when(courseRepository.findById(id)).thenReturn(Optional.of(existingCourse));
    when(courseRepository.save(existingCourse)).thenReturn(existingCourse);
    Course updated = courseService.updateCourse(id, updatedCourse);

    assertNotNull(updated);
    assertEquals(updatedCourse.getCourseName(), updated.getCourseName());
    verify(courseRepository, times(1)).findById(id);
    verify(courseRepository, times(1)).save(existingCourse);
  }

  @Test
  public void testDeleteCourse() {
    long id = 1;
    courseService.deleteCourse(id);
    verify(courseRepository, times(1)).deleteById(id);
  }
}