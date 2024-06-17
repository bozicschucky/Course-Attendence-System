package com.chucky.school.service;

import static org.junit.jupiter.api.Assertions.*;

import com.chucky.school.Adaptor.StudentDTO;
import com.chucky.school.domain.Faculty;
import com.chucky.school.domain.Student;
import com.chucky.school.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStudents() {
        Faculty faculty = new Faculty( "Dr.", null, null);
        Student student = new Student(1L, "2024-01-01", 101L, 201L, faculty);
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student));

        List<StudentDTO> students = studentService.getAllStudents();
        assertEquals(1, students.size());
        assertEquals(1L, students.get(0).getStudentId());
    }

    @Test
    void testGetStudentById() {
        Faculty faculty = new Faculty( "Dr.", null, null);
        Student student = new Student(1L, "2024-01-01", 101L, 201L, faculty);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Optional<StudentDTO> studentDTO = studentService.getStudentById(1L);
        assertTrue(studentDTO.isPresent());
        assertEquals(1L, studentDTO.get().getStudentId());
    }

    @Test
    void testCreateStudent() {
        Faculty faculty = new Faculty( "Dr.", null, null);
        Student student = new Student(1L, "2024-01-01", 101L, 201L, faculty);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(1L);
        studentDTO.setEntry("2024-01-01");
        studentDTO.setAlternateId(101L);
        studentDTO.setApplicantId(201L);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        assertEquals(1L, createdStudent.getStudentId());
    }

    @Test
    void testUpdateStudent() {
        Faculty faculty = new Faculty( "Dr.", null, null);
        Student student = new Student(1L, "2024-01-01", 101L, 201L, faculty);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(1L);
        studentDTO.setEntry("2024-01-01");
        studentDTO.setAlternateId(101L);
        studentDTO.setApplicantId(201L);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentDTO updatedStudent = studentService.updateStudent(1L, studentDTO);
        assertEquals(1L, updatedStudent.getStudentId());
    }

    @Test
    void testDeleteStudent() {
        studentService.deleteStudent(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }
}
