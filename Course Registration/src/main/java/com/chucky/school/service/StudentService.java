package com.chucky.school.service;

import com.chucky.school.Adaptor.StudentDTO;
import com.chucky.school.domain.Message;
import com.chucky.school.domain.Sender;
import com.chucky.school.domain.Student;
import com.chucky.school.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    Sender emailSender;
    @Autowired
    private StudentRepository studentRepository;

    public void sendReminderToStudent() {
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            try {
                String stringMessage = objectMapper.writeValueAsString(new Message(student.getFirstName(), "This Is Reminder For You to get Registration", student.getEmailAddress(), "Course Registration"));
                emailSender.send("StudentsMessageTopic", stringMessage);
            } catch (JsonProcessingException ignored) {
            }

        }
    }

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
        student.setStudentId(studentDTO.getStudentId());
        student.setEntry(studentDTO.getEntry());
        student.setAlternateId(studentDTO.getAlternateId());
        student.setApplicantId(studentDTO.getApplicantId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEntry(studentDTO.getEntry());
        student.setEmailAddress(studentDTO.getEmailAddress());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setCreatedRecord(studentDTO.getCreatedRecord());
        student.setUsername(studentDTO.getUsername());
        student.setPassword(studentDTO.getPassword());
        student.setStudentId(studentDTO.getStudentId());
        student.setAlternateId(studentDTO.getAlternateId());
        student.setApplicantId(studentDTO.getApplicantId());


        Student updatedStudent = studentRepository.save(student);
        return convertToDTO(updatedStudent);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    private StudentDTO convertToDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setEntry(student.getEntry());
        studentDTO.setAlternateId(student.getAlternateId());
        studentDTO.setApplicantId(student.getApplicantId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setGenderType(student.getGenderType());
        studentDTO.setEmailAddress(student.getEmailAddress());
        studentDTO.setDateOfBirth(student.getDateOfBirth());
        studentDTO.setCreatedRecord(student.getCreatedRecord());
        studentDTO.setUsername(student.getUsername());
        studentDTO.setPassword(student.getPassword());
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setEntry(student.getEntry());
        studentDTO.setAlternateId(student.getAlternateId());
        studentDTO.setApplicantId(student.getApplicantId());
        studentDTO.setGenderType(student.getGenderType());
        return studentDTO;
    }

    private Student convertToEntity(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setStudentId(studentDTO.getStudentId());
        student.setEntry(studentDTO.getEntry());
        student.setAlternateId(studentDTO.getAlternateId());
        student.setApplicantId(studentDTO.getApplicantId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEntry(studentDTO.getEntry());
        student.setEmailAddress(studentDTO.getEmailAddress());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setCreatedRecord(studentDTO.getCreatedRecord());
        student.setUsername(studentDTO.getUsername());
        student.setPassword(studentDTO.getPassword());
        student.setStudentId(studentDTO.getStudentId());
        student.setAlternateId(studentDTO.getAlternateId());
        student.setApplicantId(studentDTO.getApplicantId());
        student.setGenderType(studentDTO.getGenderType());


        return student;
    }

    public Optional<StudentDTO> getStudentBystudentID(Long studentID) {
        return studentRepository.findBystudentID(studentID).map(this::convertToDTO);
    }
}

