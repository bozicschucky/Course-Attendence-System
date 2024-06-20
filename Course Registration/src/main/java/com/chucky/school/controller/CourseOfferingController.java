package com.chucky.school.controller;

import com.chucky.school.DTO.CourseOfferingDetailsDTO;
import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.Course;
import com.chucky.school.service.CourseOfferingService;
import com.chucky.school.service.ExcelFileService1;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.ByteArrayOutputStream;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Course Offering", description = "The Course Offering API")
@RestController
public class CourseOfferingController {

    @Autowired
    private CourseOfferingService courseOfferingService;

    @Autowired
    private ExcelFileService1 excelFileService;

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

    @GetMapping(value = "sys-admin/course-offerings/{id}/attendance", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<ByteArrayResource> getCourseOfferingAttendance(@PathVariable long id) throws Exception {
        List<CourseOfferingDetailsDTO> courseOfferings = courseOfferingService.getAllCoursOffering();

        Workbook workbook = excelFileService.createExcelFile(courseOfferings);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=course_offerings.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
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
