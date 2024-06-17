package com.chucky.school.controller;

import com.chucky.school.domain.AuditData;
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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
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
    public void testCreateCourseOffering() {
        CourseOffering courseOffering = new CourseOffering();
        when(courseOfferingService.createCourseOffering(anyString(), anyLong(), anyString(), any(AuditData.class), anyLong(), anyLong()))
                .thenReturn(courseOffering);

        ResponseEntity<CourseOffering> response = courseOfferingController.createCourseOffering("Lecture", 100, "Room 101", new AuditData(), 1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courseOffering, response.getBody());
    }

    @Test
    public void testGetCourseOfferingById() {
        CourseOffering courseOffering = new CourseOffering();
        when(courseOfferingService.getCourseOfferingByIdCourse(anyLong())).thenReturn(courseOffering);

        ResponseEntity<CourseOffering> response = courseOfferingController.getCourseOfferingById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courseOffering, response.getBody());
    }

    @Test
    public void testGetCourseOfferingByIdNotFound() {
        when(courseOfferingService.getCourseOfferingByIdCourse(anyLong())).thenReturn(null);

        try {
            courseOfferingController.getCourseOfferingById(1L);
        } catch (ResourceNotFoundException e) {
            assertEquals("Course Offering not found with id 1", e.getMessage());
        }
    }

    @Test
    public void testGetAllCourseOfferings() {
        List<CourseOffering> courseOfferings = Arrays.asList(new CourseOffering(), new CourseOffering());
        when(courseOfferingService.getAllCoursOffering()).thenReturn(courseOfferings);

        ResponseEntity<List<CourseOffering>> response = courseOfferingController.getAllCourseOfferings();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courseOfferings, response.getBody());
    }

    @Test
    public void testUpdateCourseOffering() {
        CourseOffering courseOffering = new CourseOffering();
        when(courseOfferingService.updateCourseOffering(anyLong(), any(CourseOffering.class))).thenReturn(courseOffering);

        ResponseEntity<CourseOffering> response = courseOfferingController.updateCourseOffering(1L, courseOffering);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courseOffering, response.getBody());
    }

    @Test
    public void testDeleteCourseOffering() {
        ResponseEntity<Void> response = courseOfferingController.deleteCourseOffering(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetCourseOfferingsByCourseId() {
        List<CourseOffering> courseOfferings = Arrays.asList(new CourseOffering(), new CourseOffering());
        when(courseOfferingService.getCourseOfferingByCourseID(anyLong())).thenReturn(courseOfferings);

        ResponseEntity<List<CourseOffering>> response = courseOfferingController.getCourseOfferingsByCourseId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courseOfferings, response.getBody());
    }
}
