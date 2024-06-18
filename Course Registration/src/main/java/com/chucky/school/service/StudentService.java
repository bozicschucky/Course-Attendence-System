package com.chucky.school.service;

import com.chucky.school.domain.Student;
import com.chucky.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {
        Student foundStudent = studentRepository.findById(id).get();
        if (foundStudent == null) {
            return null;
        }
        student.setEntry(foundStudent.getEntry());
        student.setAlternateId(foundStudent.getAlternateId());
        student.setApplicantId(foundStudent.getApplicantId());
        student.setFacultyAdvisorId(foundStudent.getFacultyAdvisorId());
        return studentRepository.save(foundStudent);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

}


