package com.chucky.school.service;

import com.chucky.school.DTO.CourseOfferingDetailsDTO;
import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.Course;
import com.chucky.school.domain.CourseOffering;
import com.chucky.school.domain.Faculty;
import com.chucky.school.repository.CourseOfferingRepository;
import com.chucky.school.repository.CourseRepository;
import com.chucky.school.repository.FacultyRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseOfferingServiceImplTest {

    @Mock
    private CourseOfferingRepository courseOfferingRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private CourseOfferingServiceImpl courseOfferingService;

    private Course course;
    private Faculty faculty;
    private AuditData auditData;

    @BeforeEach
    void setUp() {
        course = new Course();
        faculty = new Faculty();
        auditData = new AuditData();
    }

    @Test
    void testCreateCourseOffering_Success() {
        long courseId = 544;
        long facultyId = 1L;

        when(courseRepository.findCourseById(courseId)).thenReturn(course);
        when(facultyRepository.findFacultiesById(facultyId)).thenReturn(faculty);

        CourseOfferingDetailsDTO courseOffering = courseOfferingService.createCourseOffering(
                "CS544-2024-06", 100, "V017", auditData, courseId, facultyId, LocalDate.of(2024,01, 01), LocalDate.of(2024,02, 01));

        assertNotNull(courseOffering);
        assertEquals(course.getId(), courseOffering.getCourseId());
        assertEquals(faculty.getId(), courseOffering.getFacultyId());
        verify(courseOfferingRepository, times(1)).save(any(CourseOffering.class));
    }


    @Test
    void testGetAllCourseOfferings() {
        List<CourseOfferingDetailsDTO> courseOfferings = List.of(new CourseOfferingDetailsDTO(), new CourseOfferingDetailsDTO());
        when(courseOfferingRepository.getCourseOfferingDetails()).thenReturn(courseOfferings);

        List<CourseOfferingDetailsDTO> result = courseOfferingService.getAllCoursOffering();

        assertNotNull(result);
        assertEquals(courseOfferings.size(), result.size());
    }


    @Test
    void testDeleteCourseOffering() {
        long courseOfferingId = 1L;

        doNothing().when(courseOfferingRepository).deleteById(courseOfferingId);

        courseOfferingService.deleteCourseOffering(courseOfferingId);

        verify(courseOfferingRepository, times(1)).deleteById(courseOfferingId);
    }


}
