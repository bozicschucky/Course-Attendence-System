package com.chucky.school.controller;


import com.chucky.school.Adaptor.CourseRegistrationDTO;
import com.chucky.school.service.CourseRegistrationService;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseRegistrationController {

    @Autowired
    private CourseRegistrationService courseRegistrationService;


    @PostMapping("/sys-admin/registrations")
    public ResponseEntity<CourseRegistrationDTO> createRegistration(
            @RequestParam long courseOfferingId,
            @RequestParam long studentId) {
          courseRegistrationService.createRegistration(courseOfferingId, studentId);
        return ResponseEntity.ok( CourseRegistrationDTO.builder().studentId(studentId)
                .courseOfferingId(courseOfferingId)
                .build());
    }

    @GetMapping("/sys-admin/registrations/{id}")
    public CourseRegistrationDTO getRegistrationById(@PathVariable long id) {
        CourseRegistrationDTO registration = courseRegistrationService.findRegistrationById(id);
        return registration;

    }

    @PutMapping("/sys-admin/registrations/{registrationId}")
    public ResponseEntity<CourseRegistrationDTO> updateRegistration(
            @PathVariable long registrationId,
            @RequestParam long courseOfferingId,
            @RequestParam long studentId,
            @RequestParam char grade) {
          courseRegistrationService.updateRegistration(registrationId, courseOfferingId, studentId, grade);
        return ResponseEntity.ok( CourseRegistrationDTO.builder()
                .studentId(studentId)
                .courseOfferingId(courseOfferingId)
                .grade(grade).build());
    }

    @GetMapping("/sys-admin/registrations/all")
    public ResponseEntity<List<CourseRegistrationDTO>> getAllRegistrations() {
        List<CourseRegistrationDTO> registrations = courseRegistrationService.findAllRegistrations();
        return ResponseEntity.ok(registrations);
    }

    @DeleteMapping("/sys-admin/registrations/delete/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable long id) {
        courseRegistrationService.deleteRegistration(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin-view/courseofferings/{offeringId}")
    public ResponseEntity<List<Object[]>> getCourseOfferingByIdForAdmin(@PathVariable long offeringId) {
        List<Object[]> courseOfferingAll = courseRegistrationService.getAllFromCourseOffering(offeringId);
        if (courseOfferingAll == null) {
            throw new ResourceNotFoundException("Course Offering not found with id " + offeringId);
        }
        return ResponseEntity.ok(courseOfferingAll);
    }


    @GetMapping("/student-view/student/course-offerings/{studentId}")
    public ResponseEntity<List<Object[]>> getListOfCourseForSpecificStudent(@PathVariable long studentId) {
        List<Object[]> courseOfferingAll = courseRegistrationService.getAllFromStudent(studentId);
        if (courseOfferingAll == null) {
            throw new ResourceNotFoundException("Course Offering not found with id " + studentId);
        }
        return ResponseEntity.ok(courseOfferingAll);
    }
}
