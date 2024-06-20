package com.chucky.school.service;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.chucky.school.domain.CourseOffering;
import com.chucky.school.repository.CourseRegistrationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class CourseRegistrationServiceImplTest {

    @Mock
    private CourseRegistrationRepository courseRegistrationRepository;

    @InjectMocks
    private CourseRegistrationServiceImpl courseRegistrationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testGetAllFromCourseOffering() {
//        long courseOfferingId = 1L;
//
//        Object[] data1 = {30, "Lecture", "Room 101",null, null, null, 1L, 101L, 'A'};
//        Object[] data2 = {25, "Lab", "Room 102", null, null, null, 1L, 102L, 'B'};
//        List<Object[]> mockData = Arrays.asList(data1, data2);
//
//        when(courseRegistrationRepository.getAllfromCourseOffering(courseOfferingId)).thenReturn(mockData);
//
//        List<CourseOffering> result = courseRegistrationService.getAllFromCourseOffering(courseOfferingId);
//
//        assertEquals(2, result.size());
//        assertEquals(data1, result.get().get(0));
//        assertEquals(data2, result.get(1));
//    }
}
