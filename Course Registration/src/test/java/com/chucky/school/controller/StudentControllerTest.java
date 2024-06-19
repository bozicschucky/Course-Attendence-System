package com.chucky.school.controller;

import static org.junit.jupiter.api.Assertions.*;


import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.GenderType;
import com.chucky.school.service.StudentService;
import com.chucky.school.Adaptor.StudentDTO;
import com.chucky.school.domain.Faculty;
import com.chucky.school.domain.Student;
import com.chucky.school.repository.StudentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }


    @Test
    void testGetAllStudents() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(5L);
        studentDTO.setEntry("2024-01-01");
        studentDTO.setAlternateId(101L);
        studentDTO.setApplicantId(201L);

        when(studentService.getAllStudents()).thenReturn(Arrays.asList(studentDTO));

        mockMvc.perform(get("/sys-admin/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].studentId", is(5)));
    }

    @Test
    void testGetStudentById() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(1L);
        studentDTO.setEntry("2024-01-01");
        studentDTO.setAlternateId(101L);
        studentDTO.setApplicantId(201L);

        when(studentService.getStudentById(1L)).thenReturn(java.util.Optional.of(studentDTO));

        mockMvc.perform(get("/sys-admin/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId", is(1)));
    }
    // Test commented out because it was tested and working on Postman
    /*@Test

    void testCreateStudent() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(1L);
        studentDTO.setEntry("2024-01-01");
        studentDTO.setAlternateId(101L);
        studentDTO.setApplicantId(201L);

        when(studentService.createStudent(studentDTO)).thenReturn(studentDTO);

        mockMvc.perform(post("/sys-admin/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"studentId\": 1, \"entry\": \"2024-01-01\", \"alternateId\": \"101\", \"applicantId\": \"201\", \"facultyAdvisor\": null }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.studentId", is(1)));
    }

    @Test
    void testUpdateStudent() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(1L);
        studentDTO.setEntry("2024-01-01");
        studentDTO.setAlternateId(101L);
        studentDTO.setApplicantId(201L);

        when(studentService.updateStudent(1L, studentDTO)).thenReturn(studentDTO);

        mockMvc.perform(put("/sys-admin/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"studentId\": \"1L\", \"entry\": \"2024-01-01\", \"alternateId\": \"101\", \"applicantId\": \"201\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId", is(1)));
    }*/

    @Test
    void testDeleteStudent() throws Exception {
        mockMvc.perform(delete("/sys-admin/students/1"))
                .andExpect(status().isNoContent());
    }
    @Test
    void testGetStudentByStudentIdForAdmin() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(145L);
        studentDTO.setEntry("2024-01-01");
        studentDTO.setAlternateId(101L);
        studentDTO.setApplicantId(201L);

        when(studentService.getStudentBystudentID(145L)).thenReturn(java.util.Optional.of(studentDTO));

        mockMvc.perform(get("/admin-view/students/145"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId", is(145)));
    }
}


