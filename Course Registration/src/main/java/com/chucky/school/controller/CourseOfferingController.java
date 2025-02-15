package com.chucky.school.controller;

import com.chucky.school.Adaptor.CourseOfferingDetailsDTO;
import com.chucky.school.Adaptor.SessionDTO;
import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.Course;
import com.chucky.school.domain.Session;
import com.chucky.school.service.CourseOfferingService;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
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
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam long facultyId) {
        CourseOfferingDetailsDTO courseOffering = courseOfferingService.createCourseOffering(courseOfferingType, capacity, room, new AuditData(createdBy), courseId, facultyId, startDate, endDate);
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

    @GetMapping("sys-admin/course-offerings/all")
    public ResponseEntity<List<CourseOfferingDetailsDTO>> getAllCourseOfferings() {
        List<CourseOfferingDetailsDTO> courseOfferings = courseOfferingService.getAllCoursOffering();
        return ResponseEntity.ok(courseOfferings);
    }

    @GetMapping("student-view/course-offerings/{Offeringid}/attendance")
    public ResponseEntity<?> getAllSessions(long id){
        Collection<Session> sessions = courseOfferingService.getAllSessions(id);
        Collection<SessionDTO> sessionDTO = new ArrayList<>();
        SessionDTO sessionDTO1 = new SessionDTO();
        for(Session session : sessions){
            sessionDTO1.setId(session.getId());
            sessionDTO1.setDate(session.getSessionDate());
            sessionDTO1.setSessionTitle(session.getSessionTitle());
            sessionDTO.add(sessionDTO1);
        }
        return ResponseEntity.ok(sessionDTO);
    }


    @PutMapping("sys-admin/course-offerings/{id}")
    public ResponseEntity<CourseOfferingDetailsDTO> updateCourseOffering(@PathVariable long id,
                                                                         @RequestParam String courseOfferingType,
                                                                         @RequestParam long capacity,
                                                                         @RequestParam String room,
                                                                         @RequestParam String updatedBy,
                                                                         @RequestParam long courseId,
                                                                         @RequestParam LocalDate startDate,
                                                                         @RequestParam LocalDate endDate,
                                                                         @RequestParam long facultyId) {
        CourseOfferingDetailsDTO updatedCourseOffering = courseOfferingService.updateCourseOffering(id, courseOfferingType, capacity, room, updatedBy, courseId, facultyId, startDate, endDate);
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
    @GetMapping("/admin-view/courseofferings")
    public ResponseEntity<List<Course>> getCourseOfferingsInSessionOn(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Course> courses = courseOfferingService.getCourseOfferingsInSessionOn(date);
        return ResponseEntity.ok(courses);
    }

}
