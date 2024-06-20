package com.chucky.school.service;

import com.chucky.school.Adaptor.CourseRegistrationDTO;
import com.chucky.school.domain.*;
import com.chucky.school.repository.CourseOfferingRepository;
import com.chucky.school.repository.CourseRegistrationRepository;
import com.chucky.school.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseRegistrationServiceImpl implements CourseRegistrationService {
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    StudentRepository studentRepository;
@Autowired
    Sender sender;
    @Autowired
    CourseRegistrationRepository courseRegistrationRepository;

    @Autowired
    CourseOfferingRepository courseOfferingRepository;

    @Override
    public CourseRegistration createRegistration(long courseOfferingId, long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isEmpty()) {
            throw new ResourceNotFoundException("Unable to find student with id " + studentId);
        }
        CourseOffering courseOffering = courseOfferingRepository.getCourseOfferingById(courseOfferingId);
        if (courseOffering == null) {
            throw new ResourceNotFoundException("Unable to find course offering with id " + courseOfferingId);
        }
        if (courseRegistrationRepository.countByCourseOfferingIdAndStudentId(courseOfferingId, studentId) > 0) {
            throw new ResourceNotFoundException.DuplicateRegistrationException("Course registration already exists");
        }
        CourseRegistration courseRegistration = CourseRegistration.builder()
                .courseOfferingId(courseOffering)
                .studentId(student.get()).build();
        try {
            String stringMessage= objectMapper.writeValueAsString(new Message(student.get().getFirstName(),"Registerd to new Course",student.get().getEmailAddress(),"Course Registration"));
            sender.send("StudentsMessageTopic",stringMessage );
        } catch (JsonProcessingException ignored) {
        }


        return courseRegistrationRepository.save(courseRegistration);

    }

    @Override
    public CourseRegistrationDTO findRegistrationById(long registrationId) {
        return courseRegistrationRepository.findCourseRegistrationsByCourseOfferingId(registrationId);
    }

    @Override
    public CourseRegistration updateRegistration(long registrationId,long courseOfferingId, long studentId,char grade) {
       Student student=studentRepository.findById(studentId).
        orElseThrow(() -> new ResourceNotFoundException("Unable to find the Student with provided studentId"+studentId));

  CourseOffering courseOffering= courseOfferingRepository.findById(courseOfferingId)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find the CourseOffering with provided courseOfferingId"+courseOfferingId));

        CourseRegistration findcourseRegistration= courseRegistrationRepository.findById(registrationId).
           orElseThrow(() -> new ResourceNotFoundException("Unable to find the CourseRegistration with provided registrationId"+ registrationId));
        findcourseRegistration.setGrade(grade);
        findcourseRegistration.setStudentId(student);
        findcourseRegistration.setCourseOfferingId(courseOffering);
        try {
            String stringMessage= objectMapper.writeValueAsString(new Message(student.getFirstName(),"Dear Student Course Registration is Updated",student.getEmailAddress(),"Course Registration Course Update"));
            sender.send("StudentsMessageTopic",stringMessage );
        } catch (JsonProcessingException ignored) {
        }
        return courseRegistrationRepository.save(findcourseRegistration);
    }

    @Override
    public List<CourseRegistrationDTO> findAllRegistrations() {
        return courseRegistrationRepository.findAllCourseRegistrations();
    }

    @Override
    public void deleteRegistration(long registrationId) {
        courseRegistrationRepository.deleteById(registrationId);

    }
@Override
public List<Object[]> getAllFromCourseOffering(long courseOfferingId) {
        return courseRegistrationRepository.getAllFromCourseOfferingAndFaculity(courseOfferingId);
    }

    @Override
    public List<Object[]> getAllFromStudent(long studentId) {
        return courseRegistrationRepository.getAllCourseByStudentId(studentId);
    }
}
