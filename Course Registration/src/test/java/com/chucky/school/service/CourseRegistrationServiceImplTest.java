package com.chucky.school.service;

import com.chucky.school.Adaptor.CourseRegistrationDTO;
import com.chucky.school.domain.CourseOffering;
import com.chucky.school.domain.CourseRegistration;
import com.chucky.school.domain.Student;
import com.chucky.school.repository.CourseOfferingRepository;
import com.chucky.school.repository.CourseRegistrationRepository;
import com.chucky.school.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseRegistrationServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRegistrationRepository courseRegistrationRepository;

    @Mock
    private CourseOfferingRepository courseOfferingRepository;

    @InjectMocks
    private CourseRegistrationServiceImpl courseRegistrationService;

    private Student student;
    private CourseOffering courseOffering;
    private CourseRegistration courseRegistration;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId(1L);
        courseOffering = new CourseOffering();
        courseOffering.setId(1L);
        courseRegistration = CourseRegistration.builder()
                .studentId(student)
                .courseOfferingId(courseOffering)
                .build();
    }



    @Test
    void createRegistration_duplicateRegistration() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseOfferingRepository.getCourseOfferingById(1L)).thenReturn(courseOffering);
        when(courseRegistrationRepository.countByCourseOfferingIdAndStudentId(1L, 1L)).thenReturn(1);

        ResourceNotFoundException.DuplicateRegistrationException thrown = assertThrows(
                ResourceNotFoundException.DuplicateRegistrationException.class,
                () -> courseRegistrationService.createRegistration(1L, 1L),
                "Expected createRegistration to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Course registration already exists"));
        verify(studentRepository).findById(1L);
        verify(courseOfferingRepository).getCourseOfferingById(1L);
    }

    @Test
    void updateRegistration_success() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseOfferingRepository.findById(1L)).thenReturn(Optional.of(courseOffering));
        when(courseRegistrationRepository.findById(1L)).thenReturn(Optional.of(courseRegistration));
        when(courseRegistrationRepository.save(any(CourseRegistration.class))).thenReturn(courseRegistration);

        CourseRegistration result = courseRegistrationService.updateRegistration(1L, 1L, 1L, 'A');

        assertNotNull(result);
        assertEquals(courseRegistration, result);
        verify(studentRepository).findById(1L);
        verify(courseOfferingRepository).findById(1L);
        verify(courseRegistrationRepository).findById(1L);
        verify(courseRegistrationRepository).save(any(CourseRegistration.class));
    }

    @Test
    void deleteRegistration() {
        doNothing().when(courseRegistrationRepository).deleteById(1L);

        courseRegistrationService.deleteRegistration(1L);

        verify(courseRegistrationRepository).deleteById(1L);
    }

    @Test
    void findAllRegistrations() {
        List<CourseRegistrationDTO> registrationList = List.of(new CourseRegistrationDTO());
        when(courseRegistrationRepository.findAllCourseRegistrations()).thenReturn(registrationList);

        List<CourseRegistrationDTO> result = courseRegistrationService.findAllRegistrations();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(courseRegistrationRepository).findAllCourseRegistrations();
    }

    @Test
    void findRegistrationById() {
        CourseRegistrationDTO courseRegistrationDTO = new CourseRegistrationDTO();
        when(courseRegistrationRepository.findCourseRegistrationsByCourseOfferingId(1L)).thenReturn(courseRegistrationDTO);

        CourseRegistrationDTO result = courseRegistrationService.findRegistrationById(1L);

        assertNotNull(result);
        assertEquals(courseRegistrationDTO, result);
        verify(courseRegistrationRepository).findCourseRegistrationsByCourseOfferingId(1L);
    }


}
