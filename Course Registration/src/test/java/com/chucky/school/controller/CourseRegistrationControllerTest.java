package com.chucky.school.controller;

import com.chucky.school.DTO.CourseRegistrationDTO;
import com.chucky.school.service.CourseRegistrationService;
import exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseRegistrationControllerTest {

    @Mock
    private CourseRegistrationService courseRegistrationService;

    @InjectMocks
    private CourseRegistrationController courseRegistrationController;

    private CourseRegistrationDTO courseRegistrationDTO;

    @BeforeEach
    void setUp() {
        courseRegistrationDTO = CourseRegistrationDTO.builder()
                .studentId(1L)
                .courseOfferingId(1L)
                .grade('A')
                .build();
    }

    @Test
    void getRegistrationById_success() {
        when(courseRegistrationService.findRegistrationById(1L)).thenReturn(courseRegistrationDTO);

        CourseRegistrationDTO response = courseRegistrationController.getRegistrationById(1L);

        assertNotNull(response);
        assertEquals(courseRegistrationDTO, response);
        verify(courseRegistrationService).findRegistrationById(1L);
    }

    @Test
    void getAllRegistrations_success() {
        List<CourseRegistrationDTO> registrationList = List.of(courseRegistrationDTO);
        when(courseRegistrationService.findAllRegistrations()).thenReturn(registrationList);

        ResponseEntity<List<CourseRegistrationDTO>> response = courseRegistrationController.getAllRegistrations();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(courseRegistrationService).findAllRegistrations();
    }

    @Test
    void deleteRegistration_success() {
        doNothing().when(courseRegistrationService).deleteRegistration(1L);

        ResponseEntity<Void> response = courseRegistrationController.deleteRegistration(1L);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(courseRegistrationService).deleteRegistration(1L);
    }



    @Test
    void getCourseOfferingByIdForAdmin_notFound() {
        when(courseRegistrationService.getAllFromCourseOffering(1L)).thenReturn(null);

        ResourceNotFoundException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> courseRegistrationController.getCourseOfferingByIdForAdmin(1L),
                "Expected getCourseOfferingByIdForAdmin to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Course Offering not found with id 1"));
        verify(courseRegistrationService).getAllFromCourseOffering(1L);
    }

}
