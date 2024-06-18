package com.chucky.school.controller;

import com.chucky.school.service.StudentService;
import com.chucky.school.Adaptor.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Student", description = "The Student API")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/sys-admin/students")
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/sys-admin/students/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO student = studentService.getStudentById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        return ResponseEntity.ok(student);
    }

    @PostMapping("/sys-admin/students")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @PutMapping("/sys-admin/students/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/sys-admin/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/admin-view/students/{studentID}")
    public ResponseEntity<StudentDTO> getStudentByIdForAdmin(@PathVariable Long studentID) {
        StudentDTO student = studentService.getStudentBystudentID(studentID).orElseThrow(() -> new RuntimeException("Student not found"));
        return ResponseEntity.ok(student);
    }
}
