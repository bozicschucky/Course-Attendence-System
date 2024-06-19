package com.chucky.school.controller;


import com.chucky.school.domain.CourseRegistration;
import com.chucky.school.service.CourseRegistrationService;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseRegistrationController {

    @Autowired
    private CourseRegistrationService courseRegistrationService;


    @PostMapping("/sys-admin/registrations")
    public ResponseEntity<CourseRegistration> createRegistration(
            @RequestParam long courseOfferingId,
            @RequestParam long studentId) {
        CourseRegistration registration = courseRegistrationService.createRegistration(courseOfferingId, studentId);
        return ResponseEntity.ok(registration);
    }

    @GetMapping("/sys-admin/registrations/{id}")
    public ResponseEntity<CourseRegistration> getRegistrationById(@PathVariable long id) {
        Optional<CourseRegistration> registration = courseRegistrationService.findRegistrationById(id);
        return registration.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found with id " + id));
    }

    @PutMapping("/sys-admin/registrations/{registrationId}")
    public ResponseEntity<CourseRegistration> updateRegistration(
            @PathVariable long registrationId,
            @RequestParam long courseOfferingId,
            @RequestParam long studentId,
            @RequestParam char grade) {
        CourseRegistration updatedRegistration = courseRegistrationService.updateRegistration(registrationId, courseOfferingId, studentId, grade);
        return ResponseEntity.ok(updatedRegistration);
    }

    @GetMapping("/sys-admin/registrations/all")
    public ResponseEntity<List<CourseRegistration>> getAllRegistrations() {
        List<CourseRegistration> registrations = courseRegistrationService.findAllRegistrations();
        return ResponseEntity.ok(registrations);
    }

    @DeleteMapping("/sys-admin/registrations/delete/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable long id) {
        courseRegistrationService.deleteRegistration(id);
        return ResponseEntity.noContent().build();
    }
}
