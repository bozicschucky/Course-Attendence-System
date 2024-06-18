package com.chucky.school.controller;

import com.chucky.school.DTO.CourseOfferingDetailsDTO;
import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.CourseOffering;
import com.chucky.school.service.CourseOfferingService;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Course Offering", description = "The Course Offering API")
@RestController
public class CourseOfferingController {

    @Autowired
    private CourseOfferingService courseOfferingService;

    @PostMapping("sys-admin/course-offerings")
    public ResponseEntity<CourseOfferingDetailsDTO> createCourseOffering(
            @RequestParam String courseOfferingType,
            @RequestParam long capacity,
            @RequestParam String room,
            @RequestParam String createdBy,
            @RequestParam long courseId,
            @RequestParam long facultyId) {
        CourseOfferingDetailsDTO courseOffering = courseOfferingService.createCourseOffering(courseOfferingType, capacity, room, new AuditData(createdBy), courseId, facultyId);
        return ResponseEntity.ok(courseOffering);
    }

    @GetMapping("sys-admin/course-offerings/{id}")
    public ResponseEntity<CourseOfferingDetailsDTO> getCourseOfferingById(@PathVariable long id) {
        CourseOfferingDetailsDTO courseOffering = courseOfferingService.getCourseOfferingByID(id);
        if (courseOffering == null) {
            throw new ResourceNotFoundException("Course Offering not found with id " + id);
        }
        return ResponseEntity.ok(courseOffering);
    }

    @GetMapping("sys-admin/courseOfferings/details")
    public List<CourseOfferingDetailsDTO> getCourseOfferingDetails() {
        return courseOfferingService.getCourseOfferingDetails();
    }

    @GetMapping("sys-admin/course-offerings/all")
    public ResponseEntity<List<CourseOffering>> getAllCourseOfferings() {
        List<CourseOffering> courseOfferings = courseOfferingService.getAllCoursOffering();
        return ResponseEntity.ok(courseOfferings);
    }


    @PutMapping("sys-admin/course-offerings/{id}")
    public ResponseEntity<CourseOfferingDetailsDTO> updateCourseOffering(@PathVariable long id,
                                                                         @RequestParam String courseOfferingType,
                                                                         @RequestParam long capacity,
                                                                         @RequestParam String room,
                                                                         @RequestParam String updatedBy,
                                                                         @RequestParam long courseId,
                                                                         @RequestParam long facultyId) {
        CourseOfferingDetailsDTO updatedCourseOffering = courseOfferingService.updateCourseOffering(id, courseOfferingType, capacity, room, updatedBy, courseId, facultyId);
        return ResponseEntity.ok(updatedCourseOffering);
    }
    @DeleteMapping("sys-admin/course-offerings/remove/{id}")
    public ResponseEntity<Void> deleteCourseOffering(@PathVariable long id) {
        courseOfferingService.deleteCourseOffering(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("student-view/course-offerings/{courseOfferingid}")
    public ResponseEntity<CourseOfferingDetailsDTO> getCourseOfferingByIdForStudent(@PathVariable long courseOfferingid) {
        CourseOfferingDetailsDTO courseOffering = courseOfferingService.getCourseOfferingByID(courseOfferingid);
        if (courseOffering == null) {
            throw new ResourceNotFoundException("Course Offering not found with id " + courseOfferingid);
        }
        return ResponseEntity.ok(courseOffering);
    }


}
