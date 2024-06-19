package com.chucky.school.service;

import com.chucky.school.domain.CourseOffering;
import com.chucky.school.domain.CourseRegistration;
import com.chucky.school.domain.Student;
import com.chucky.school.repository.CourseOfferingRepository;
import com.chucky.school.repository.CourseRegistrationRepository;
import com.chucky.school.repository.StudentRepository;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseRegistrationServiceImpl implements CourseRegistrationService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRegistrationRepository courseRegistrationRepository;

    @Autowired
    CourseOfferingRepository courseOfferingRepository;

    @Override
    public CourseRegistration createRegistration(long courseOfferingId, long studentId) {
        Optional<Student> student = studentRepository.findBystudentID(studentId);
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
        CourseRegistration courseRegistrationResponse = courseRegistrationRepository.save(courseRegistration);

        return courseRegistrationResponse;

    }

    @Override
    public Optional<CourseRegistration> findRegistrationById(long registrationId) {
        return courseRegistrationRepository.findById(registrationId);
    }

    @Override
    public CourseRegistration updateRegistration(long registrationId,long courseOfferingId, long studentId,char grade) {
       Student student=studentRepository.findBystudentID(studentId).
        orElseThrow(() -> new ResourceNotFoundException("Unable to find the Student with provided studentId"+studentId));

  CourseOffering courseOffering= courseOfferingRepository.findById(courseOfferingId)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find the CourseOffering with provided courseOfferingId"+courseOfferingId));

        CourseRegistration findcourseRegistration= courseRegistrationRepository.findById(registrationId).
           orElseThrow(() -> new ResourceNotFoundException("Unable to find the CourseRegistration with provided registrationId"+ registrationId));
        findcourseRegistration.setGrade(grade);
        findcourseRegistration.setStudentId(student);
        findcourseRegistration.setCourseOfferingId(courseOffering);
        return courseRegistrationRepository.save(findcourseRegistration);
    }

    @Override
    public List<CourseRegistration> findAllRegistrations() {
        return courseRegistrationRepository.findAll();
    }

    @Override
    public void deleteRegistration(long registrationId) {
        courseRegistrationRepository.deleteById(registrationId);

    }

    public List<Object[]> getAllFromCourseOffering(long courseOfferingId) {
        return courseRegistrationRepository.getAllfromCourseOffering(courseOfferingId);
    }
}
