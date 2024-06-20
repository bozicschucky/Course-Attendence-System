package com.chucky.school.controller;

import com.chucky.school.DTO.CourseOfferingDetailsDTO;
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
    public void testGetCourseOfferingByIdForStudent() {
        CourseOfferingDetailsDTO courseOffering = new CourseOfferingDetailsDTO();
        when(courseOfferingService.getCourseOfferingByID(anyLong())).thenReturn(courseOffering);

        ResponseEntity<CourseOfferingDetailsDTO> response = courseOfferingController.getCourseOfferingByIdForStudent(1L);

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
        List<CourseOffering> courseOfferings = Arrays.asList(new CourseOffering(), new CourseOffering());
        when(courseOfferingService.getAllCoursOffering()).thenReturn(courseOfferings);

        ResponseEntity<List<CourseOffering>> response = courseOfferingController.getAllCourseOfferings();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courseOfferings, response.getBody());
    }


    @Test
    public void testDeleteCourseOffering() {
        ResponseEntity<Void> response = courseOfferingController.deleteCourseOffering(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
