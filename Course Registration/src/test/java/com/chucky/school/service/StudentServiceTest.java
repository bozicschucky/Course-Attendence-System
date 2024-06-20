package com.chucky.school.service;

import static org.junit.jupiter.api.Assertions.*;

import com.chucky.school.Adaptor.StudentDTO;
import com.chucky.school.domain.*;
import com.chucky.school.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.time.LocalDate;
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
        Faculty faculty = new Faculty("Payman", "Salek",GenderType.MALE, "payman@miu.edu.com",
                LocalDate.of(1960, 04,17), null, "paysalek","pay123" , "Faculty", "Dr.", null, null);
        Student student = new Student("Eman", "Shemsu", GenderType.FEMALE, "eman@miu.edu", LocalDate.of(1995, 12,18), null, "emanawel", "eman123",
                7L, "2023-10-03", 101L, 201L, faculty) ;
        Student student2 = new Student("Eman", "Shemsu", GenderType.FEMALE, "eman@miu.edu", LocalDate.of(1995, 12,18), null, "emanawel", "eman123",
                5L, "2023-10-03", 101L, 201L, faculty) ;

        when(studentRepository.findAll()).thenReturn(Arrays.asList(student, student2));
        List<StudentDTO> students = studentService.getAllStudents();
        assertEquals(2, students.size());
    }

    @Test
    void testGetStudentById() {
        Faculty faculty = new Faculty("Payman", "Salek",GenderType.MALE, "payman@miu.edu.com",
                LocalDate.of(1960, 04,17), null, "paysalek","pay123" , "Faculty", "Dr.", null, null);
        Student student = new Student("Eman", "Shemsu", GenderType.FEMALE, "eman@miu.edu", LocalDate.of(1995, 12,18), null, "emanawel", "eman123",
                2L, "2023-10-03", 101L, 201L, faculty) ;

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Optional<StudentDTO> studentDTO = studentService.getStudentById(1L);
        assertTrue(studentDTO.isPresent());
        assertEquals(0, studentDTO.get().getId());
    }

    @Test
    void testCreateStudent() {
        Faculty faculty = new Faculty("Payman", "Salek",GenderType.MALE, "payman@miu.edu.com",
                LocalDate.of(1960, 04,17), null, "paysalek","pay123" , "Faculty", "Dr.", null, null);
        Student student = new Student("Eman", "Shemsu", GenderType.FEMALE, "eman@miu.edu", LocalDate.of(1995, 12,18), null, "emanawel", "eman123", 2L,
                "2023-10-03", 101L, 201L, faculty) ;

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(5L);
        studentDTO.setEntry("2024-01-01");
        studentDTO.setAlternateId(101L);
        studentDTO.setApplicantId(201L);


        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        assertEquals(2L, createdStudent.getStudentId());
    }

    @Test
    void testUpdateStudent() {
        Faculty faculty = new Faculty("Payman", "Salek",GenderType.MALE, "payman@miu.edu.com",
                LocalDate.of(1960, 04,17), null, "paysalek","pay123" , "Faculty", "Dr.", null, null);
        Student student = new Student("Eman", "Shemsu", GenderType.FEMALE, "eman@miu.edu", LocalDate.of(1995, 12,18), null, "emanawel", "eman123", 2L,
                "2023-10-03", 101L, 201L, faculty) ;
        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setStudentId(10L);
        studentDTO.setEntry("2024-01-01");
        studentDTO.setAlternateId(102L);
        studentDTO.setApplicantId(201L);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentDTO updatedStudent = studentService.updateStudent(1L, studentDTO);
        assertEquals(102L, updatedStudent.getAlternateId());
    }

    @Test
    void testDeleteStudent() {
        studentService.deleteStudent(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetStudentBystudentId() {
        Faculty faculty = new Faculty("Payman", "Salek",GenderType.MALE, "payman@miu.edu.com",
                LocalDate.of(1960, 04,17), null, "paysalek","pay123" , "Faculty", "Dr.", null, null);
        Student student = new Student("Eman", "Shemsu", GenderType.FEMALE, "eman@miu.edu", LocalDate.of(1995, 12,18), null,
                "emanawel", "eman123", 12L,
                "2023-10-03", 101L, 201L, faculty) ;
        when(studentRepository.findBystudentID(12L)).thenReturn(Optional.of((student)));
        Optional<StudentDTO> studentDTO = studentService.getStudentBystudentID(12L);
        assertTrue(studentDTO.isPresent());
        assertEquals(12, studentDTO.get().getStudentId());
    }
    @Test
    void testGetStudentBystudentId_Not_Found() {
        Faculty faculty = new Faculty("Payman", "Salek",GenderType.MALE, "payman@miu.edu.com",
                LocalDate.of(1960, 04,17), null, "paysalek","pay123" , "Faculty", "Dr.", null, null);
        Student student = new Student("Eman", "Shemsu", GenderType.FEMALE, "eman@miu.edu", LocalDate.of(1995, 12,18), null, "emanawel", "eman123",
                22L, "2023-10-03", 101L, 201L, faculty) ;
        when(studentRepository.findBystudentID(22L)).thenReturn(Optional.of((student)));
        Optional<StudentDTO> studentDTO = studentService.getStudentBystudentID(2L);
        assertTrue(!studentDTO.isPresent());
    }
}
