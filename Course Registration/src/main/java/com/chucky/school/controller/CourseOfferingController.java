package com.chucky.school.controller;

import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.CourseOffering;
import com.chucky.school.service.CourseOfferingService;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseOfferingController {

    @Autowired
    private CourseOfferingService courseOfferingService;

    @PostMapping
    public ResponseEntity<CourseOffering> createCourseOffering(
            @RequestParam String courseOfferingType,
            @RequestParam long capacity,
            @RequestParam String room,
            @RequestParam String createdBy,
            @RequestParam long courseId,
            @RequestParam long facultyId) {
        CourseOffering courseOffering = courseOfferingService.createCourseOffering(courseOfferingType, capacity, room, new AuditData(createdBy), courseId, facultyId);
        return ResponseEntity.ok(courseOffering);
    }

    @GetMapping("sys-admin/course-offerings/{id}")
    public ResponseEntity<CourseOffering> getCourseOfferingById(@PathVariable long id) {
        CourseOffering courseOffering = courseOfferingService.getCourseOfferingByID(id);
        if (courseOffering == null) {
            throw new ResourceNotFoundException("Course Offering not found with id " + id);
        }
        return ResponseEntity.ok(courseOffering);
    }

    @GetMapping("sys-admin/course-offerings/all")
    public ResponseEntity<List<CourseOffering>> getAllCourseOfferings() {
        List<CourseOffering> courseOfferings = courseOfferingService.getAllCoursOffering();
        return ResponseEntity.ok(courseOfferings);
    }

    @PutMapping("sys-admin/course-offerings/{id}")
    public ResponseEntity<CourseOffering> updateCourseOffering(@PathVariable long id, @RequestBody CourseOffering courseOffering) {
        CourseOffering updatedCourseOffering = courseOfferingService.updateCourseOffering(id, courseOffering);
        return ResponseEntity.ok(updatedCourseOffering);
    }

    @DeleteMapping("sys-admin/course-offerings/remove/{id}")
    public ResponseEntity<Void> deleteCourseOffering(@PathVariable long id) {
        courseOfferingService.deleteCourseOffering(id);
        return ResponseEntity.noContent().build();
    }


}
