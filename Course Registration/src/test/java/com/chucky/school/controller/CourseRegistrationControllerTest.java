package com.chucky.school.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.chucky.school.DTO.CourseOfferingDetailsDTO;
import com.chucky.school.service.CourseOfferingService;
import com.chucky.school.service.CourseRegistrationService;
import com.chucky.school.service.CourseRegistrationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

//@WebMvcTest(CourseRegistrationController.class)
public class CourseRegistrationControllerTest {
/*
    @InjectMocks
    private CourseOfferingController courseOfferingController;

    @Mock
    private CourseOfferingService courseOfferingService;

    @Autowired
    private MockMvc mockMvc;*/

    @InjectMocks
    private CourseRegistrationController courseRegistrationController;

    @Mock
    private CourseRegistrationService courseRegistrationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFromCourseOffering() throws Exception {
        long courseOfferingId = 1L;
        Object[] data1 = {30, "Lecture", "Room 101",null, null, null, 1L, 101L, 'A'};
        Object[] data2 = {25, "Lab", "Room 102", null, null, null, 1L, 102L, 'B'};

        List<Object[]> mockData = Arrays.asList(data1, data2);

        when(courseRegistrationService.getAllFromCourseOffering(courseOfferingId)).thenReturn(mockData);
        ResponseEntity<List<Object[]>> response = courseRegistrationController.getCourseOfferingByIdForAdmin(courseOfferingId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockData, response.getBody());


    }


}
