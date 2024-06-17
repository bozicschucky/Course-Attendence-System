package com.chucky.school.service;

import com.chucky.school.Adaptor.StudentDTO;
import com.chucky.school.domain.Faculty;
import com.chucky.school.domain.Student;
import com.chucky.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<StudentDTO> getStudentById(Long id) {
        return studentRepository.findById(id).map(this::convertToDTO);
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return convertToDTO(savedStudent);
    }

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setEntry(studentDTO.getEntry());
        student.setAlternateId(studentDTO.getAlternateId());
        student.setApplicantId(studentDTO.getApplicantId());

        //Faculty faculty = new Faculty();
        //faculty.setFacultyAdvisorId(studentDTO.getFacultyAdvisorId());
        //student.setFacultyAdvisorId(faculty);

        Student updatedStudent = studentRepository.save(student);
        return convertToDTO(updatedStudent);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    private StudentDTO convertToDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setEntry(student.getEntry());
        studentDTO.setAlternateId(student.getAlternateId());
        studentDTO.setApplicantId(student.getApplicantId());
        //studentDTO.setFacultyAdvisorId(student.getFacultyAdvisorId() != null ? student.getFacultyAdvisorId().getFacultyAdvisorId() : null);
        return studentDTO;
    }

    private Student convertToEntity(StudentDTO studentDTO) {
        Student student = new Student();
        student.setStudentId(studentDTO.getStudentId());
        student.setEntry(studentDTO.getEntry());
        student.setAlternateId(studentDTO.getAlternateId());
        student.setApplicantId(studentDTO.getApplicantId());

        /*if (studentDTO.getFacultyAdvisorId() != null) {
            Faculty faculty = new Faculty();
            //faculty.setFacultyAdvisorId(studentDTO.getFacultyAdvisorId());
            student.setFacultyAdvisorId(faculty);
        } else {
            student.setFacultyAdvisorId(null);
        }*/

        return student;
    }
}


