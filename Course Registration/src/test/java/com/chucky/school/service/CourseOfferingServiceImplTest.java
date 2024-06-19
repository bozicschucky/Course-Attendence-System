package com.chucky.school.service;

import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.Course;
import com.chucky.school.domain.CourseOffering;
import com.chucky.school.domain.Faculty;
import com.chucky.school.dto.CourseOfferingDetailsDTO;
import com.chucky.school.repository.CourseOfferingRepository;
import com.chucky.school.repository.CourseRepository;
import com.chucky.school.repository.FacultyRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

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
                "CS544-2024-06", 100, "V017", auditData, courseId, facultyId);

        assertNotNull(courseOffering);
        assertEquals(course.getId(), courseOffering.getCourseId());
        assertEquals(faculty.getId(), courseOffering.getFacultyId());
        verify(courseOfferingRepository, times(1)).save(any(CourseOffering.class));
    }


    @Test
    void testGetAllCourseOfferings() {
        List<CourseOffering> courseOfferings = List.of(new CourseOffering(), new CourseOffering());
        when(courseOfferingRepository.findAll()).thenReturn(courseOfferings);

        List<CourseOffering> result = courseOfferingService.getAllCoursOffering();

        assertNotNull(result);
        assertEquals(courseOfferings.size(), result.size());
    }

    @Test
    void testGetCourseOfferings() {
        CourseOfferingDetailsDTO courseOfferingDTO = new CourseOfferingDetailsDTO();
        long courseofferingId = 1L;
        when(courseOfferingRepository.getCourseOfferingDTOById(courseofferingId)).thenReturn(courseOfferingDTO);
        CourseOfferingDetailsDTO result = courseOfferingService.getCourseOfferingByID(courseofferingId);
        verify(courseOfferingRepository, times(1)).getCourseOfferingDTOById(courseofferingId);
        assertEquals(courseOfferingDTO, result);

    }

//    @Test
//    void testUpdateCourseOffering() {
//        long courseOfferingId = 1L;
//        CourseOffering existingCourseOffering = new CourseOffering();
//        CourseOffering newCourseOffering = new CourseOffering();
//
//        when(courseOfferingRepository.getReferenceById(courseOfferingId)).thenReturn(existingCourseOffering);
//        when(courseOfferingRepository.save(any(CourseOffering.class))).thenReturn(existingCourseOffering);
//
//        CourseOfferingDetailsDTO result = courseOfferingService.updateCourseOffering(courseOfferingId, newCourseOffering);
//
//        assertNotNull(result);
//        verify(courseOfferingRepository, times(1)).save(existingCourseOffering);
//    }

    @Test
    void testDeleteCourseOffering() {
        long courseOfferingId = 1L;

        doNothing().when(courseOfferingRepository).deleteById(courseOfferingId);

        courseOfferingService.deleteCourseOffering(courseOfferingId);

        verify(courseOfferingRepository, times(1)).deleteById(courseOfferingId);
    }


}
