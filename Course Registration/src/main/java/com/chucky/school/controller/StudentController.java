package com.chucky.school.controller;

import com.chucky.school.service.FacultyService;
import com.chucky.school.service.StudentService;
import com.chucky.school.Adaptor.StudentDTO;
import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.Faculty;
import com.chucky.school.domain.GenderType;
import com.chucky.school.domain.Student;

import org.checkerframework.checker.units.qual.g;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Tag(name = "Student", description = "The Student API")
@RestController
@RequestMapping("/sys-admin/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private FacultyService facultyService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }


    @PostMapping
    public ResponseEntity<Student> createStudent(
            @RequestParam(value = "firstName", required = true) String firstName,
            @RequestParam(value = "lastName", required = true) String lastName,
            @RequestParam(value = "genderType", required = true) String genderType,
            @RequestParam(value = "emailAddress", required = true) String emailAddress,
            @RequestParam(value = "dateOfBirth", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
            @RequestParam(value = "createdBy", required = true) String createdBy,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "studentId", required = true) long studentId,
            @RequestParam(value = "entry", required = true) String entry,
            @RequestParam(value = "alternateId", required = true) long alternateId,
            @RequestParam(value = "applicantId", required = true) long applicantId,
            @RequestParam(value = "facultyAdvisorId", required = true) long facultyAdvisorId) {
        // check if the genderType is Male or Female or Other
        GenderType chosenGenderType;
        if (genderType.equalsIgnoreCase("male")) {
            chosenGenderType = GenderType.MALE;
        } else if (genderType.equalsIgnoreCase("female")) {
            chosenGenderType = GenderType.FEMALE;
        } else {
            chosenGenderType = GenderType.OTHER;
        }

        // get the faculty advisor by id
        Faculty facultyAdvisor = facultyService.getFacultyById(facultyAdvisorId);

        Student student = new Student(firstName, lastName,
                chosenGenderType, emailAddress, dateOfBirth, new AuditData(createdBy), username, password, studentId,
                entry,
                alternateId, applicantId, facultyAdvisor);
        Student createdStudent = studentService.createStudent(student);
        if (createdStudent == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestParam(value = "firstName", required = false) Optional<String> firstName,
            @RequestParam(value = "lastName", required = false) Optional<String> lastName,
            @RequestParam(value = "genderType", required = false) Optional<String> genderType,
            @RequestParam(value = "emailAddress", required = false) Optional<String> emailAddress,
            @RequestParam(value = "dateOfBirth", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> dateOfBirth,
            @RequestParam(value = "updatedBy", required = false) Optional<String> updatedBy,
            @RequestParam(value = "username", required = false) Optional<String> username,
            @RequestParam(value = "password", required = false) Optional<String> password,
            @RequestParam(value = "studentId", required = false) Optional<Long> studentId,
            @RequestParam(value = "entry", required = false) Optional<String> entry,
            @RequestParam(value = "alternateId", required = false) Optional<Long> alternateId,
            @RequestParam(value = "applicantId", required = false) Optional<Long> applicantId,
            @RequestParam(value = "facultyAdvisorId", required = false) Optional<Long> facultyAdvisorId) {

        // get the existing student
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        // update the fields if provided
        firstName.ifPresent(student::setFirstName);
        lastName.ifPresent(student::setLastName);
        genderType.ifPresent(g -> {
            GenderType chosenGenderType;
            if (g.equalsIgnoreCase("male")) {
                chosenGenderType = GenderType.MALE;
            } else if (g.equalsIgnoreCase("female")) {
                chosenGenderType = GenderType.FEMALE;
            } else {
                chosenGenderType = GenderType.OTHER;
            }
            student.setGenderType(chosenGenderType);
        });
        emailAddress.ifPresent(student::setEmailAddress);
        dateOfBirth.ifPresent(student::setDateOfBirth);
        updatedBy.ifPresent(u -> student.getCreatedRecord().setUpdatedBy(u));
        username.ifPresent(student::setUsername);
        password.ifPresent(student::setPassword);
        studentId.ifPresent(student::setStudentId);
        entry.ifPresent(student::setEntry);
        alternateId.ifPresent(student::setAlternateId);
        applicantId.ifPresent(student::setApplicantId);
        facultyAdvisorId.ifPresent(f -> {
            Faculty facultyAdvisor = facultyService.getFacultyById(f);
            if (facultyAdvisor != null)
                student.setFacultyAdvisorId(facultyAdvisor);
        });

        // save the updated student
        Student updatedStudent = studentService.updateStudent(id, student);
        if (updatedStudent == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
