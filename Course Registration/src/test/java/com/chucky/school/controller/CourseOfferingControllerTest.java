package com.chucky.school.controller;

import com.chucky.school.DTO.CourseOfferingDetailsDTO;
import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.Course;
import com.chucky.school.domain.CourseOffering;
import com.chucky.school.service.CourseOfferingService;
import exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.when;

public class CourseOfferingControllerTest {

    @InjectMocks
    private CourseOfferingController courseOfferingController;

    @Mock
    private CourseOfferingService courseOfferingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetCourseOfferingById() {
        CourseOfferingDetailsDTO courseOffering = new CourseOfferingDetailsDTO();
        when(courseOfferingService.getCourseOfferingByID(anyLong())).thenReturn(courseOffering);

        ResponseEntity<CourseOfferingDetailsDTO> response = courseOfferingController.getCourseOfferingById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courseOffering, response.getBody());
    }

    @Test
    public void testGetCourseOfferingByIdNotFound() {
        when(courseOfferingService.getCourseOfferingByID(anyLong())).thenReturn(null);

        try {
            courseOfferingController.getCourseOfferingById(1L);
        } catch (ResourceNotFoundException e) {
            assertEquals("Course Offering not found with id 1", e.getMessage());
        }
    }

    @Test
    public void testGetAllCourseOfferings() {
        List<CourseOfferingDetailsDTO> courseOfferings = Arrays.asList(new CourseOfferingDetailsDTO(), new CourseOfferingDetailsDTO());
        when(courseOfferingService.getAllCoursOffering()).thenReturn(courseOfferings);
        //ResponseEntity<List<CourseOfferingDetailsDTO>>
                var response = courseOfferingController.getAllCourseOfferings();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courseOfferings, response.getBody());
    }


    @Test
    public void testDeleteCourseOffering() {
        ResponseEntity<Void> response = courseOfferingController.deleteCourseOffering(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
    @Test
    void  testgetCourseOfferingsInSessionOn(){
        Course course1 = new Course(50, "cs45", "12345", "EA", "CS", null);
        Course course2 = new Course(50, "cs32", "12345", "WAA", "CS", null);

        CourseOffering courseOffering1 = CourseOffering.builder()
                .capacity(40)
                .courseOfferingType("Type1")
                .room("607")
                .auditData(null)
                .startDate(LocalDate.of(2024,01,01))
                .endDate(LocalDate.of(2024,01,30))
                .course(course1).build();
        CourseOffering courseOffering2 = CourseOffering.builder()
                .capacity(40)
                .courseOfferingType("Type2")
                .room("777")
                .auditData(null)
                .startDate(LocalDate.of(2024,01,04))
                .endDate(LocalDate.of(2024,02,04))
                .course(course2).build();


        List<Course> courses = List.of(course1, course2);

        when(courseOfferingService.getCourseOfferingsInSessionOn(LocalDate.of(2024,01,05))).thenReturn(courses);
        ResponseEntity<List<Course>> response = courseOfferingController.getCourseOfferingsInSessionOn(LocalDate.of(2024,01,05));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courses, response.getBody());


    }
    @Test
    void  testgetCourseOfferingsInSessionOn_Not_found(){
        Course course1 = new Course(50, "cs45", "12345", "EA", "CS", null);
        Course course2 = new Course(50, "cs32", "12345", "WAA", "CS", null);

        CourseOffering courseOffering1 = CourseOffering.builder()
                .capacity(40)
                .courseOfferingType("Type1")
                .room("607")
                .auditData(null)
                .startDate(LocalDate.of(2024,01,01))
                .endDate(LocalDate.of(2024,01,30))
                .course(course1).build();
        CourseOffering courseOffering2 = CourseOffering.builder()
                .capacity(40)
                .courseOfferingType("Type2")
                .room("777")
                .auditData(null)
                .startDate(LocalDate.of(2024,01,04))
                .endDate(LocalDate.of(2024,02,04))
                .course(course2).build();


        List<Course> courses = List.of();

        when(courseOfferingService.getCourseOfferingsInSessionOn(LocalDate.of(2024,10,05))).thenReturn(courses);
        ResponseEntity<List<Course>> response = courseOfferingController.getCourseOfferingsInSessionOn(LocalDate.of(2024,10,05));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courses, response.getBody());


    }

}
