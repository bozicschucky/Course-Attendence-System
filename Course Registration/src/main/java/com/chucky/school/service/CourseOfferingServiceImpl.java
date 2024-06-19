package com.chucky.school.service;

import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.Course;
import com.chucky.school.domain.CourseOffering;
import com.chucky.school.domain.Faculty;
import com.chucky.school.dto.CourseOfferingDetailsDTO;
import com.chucky.school.repository.CourseOfferingRepository;
import com.chucky.school.repository.CourseRepository;
import com.chucky.school.repository.FacultyRepository;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseOfferingServiceImpl implements CourseOfferingService {
    @Autowired
    CourseOfferingRepository courseOfferingRepository;
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @Override
    public CourseOfferingDetailsDTO createCourseOffering(String courseOfferingType, long capacity, String room, AuditData auditData, long courseId, long facultyId) {

        Course course = courseRepository.findCourseById(courseId);
        if (course == null) {
            throw new ResourceNotFoundException("Unable to Find the course with provided courseID ");
        }

        Faculty faculty = facultyRepository.findFacultiesById(facultyId);
        if (faculty == null) {
            throw new ResourceNotFoundException("Unable to Find the Faculty with provided FacultyId ");
        }
        CourseOffering courseOffering = CourseOffering.builder()
                .capacity(capacity)
                .course(course)
                .courseOfferingType(courseOfferingType)
                .room(room)
                .auditData(auditData)
                .faculty(faculty)
                .build();
        courseOfferingRepository.save(courseOffering);

        return CourseOfferingDetailsDTO.builder()
                .courseOfferingType(courseOffering.getCourseOfferingType())
                .courseId(courseOffering.getCourse().getId())
                .facultyId(courseOffering.getFaculty().getId())
                .capacity(courseOffering.getCapacity())
                .room(courseOffering.getRoom()).build();
    }

    public List<CourseOfferingDetailsDTO> getCourseOfferingDetails() {
        return courseOfferingRepository.getCourseOfferingDetails();
    }

    @Override
    public List<CourseOffering> getAllCoursOffering() {
        return courseOfferingRepository.findAll();
    }

    @Override
    public CourseOfferingDetailsDTO updateCourseOffering(long id, String courseOfferingType, long capacity, String room, String updatedBy, long courseId, long facultyId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find the course with provided courseId"));

        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find the faculty with provided facultyId"));


        CourseOffering courseOfferingToUpdate = courseOfferingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find the course offering with provided id"));


        AuditData auditData = courseOfferingToUpdate.getAuditData();
        auditData.setUpdatedBy(updatedBy);
        auditData.setUpdatedOn(LocalDateTime.now());

        courseOfferingToUpdate.setCourseOfferingType(courseOfferingType);
        courseOfferingToUpdate.setCapacity(capacity);
        courseOfferingToUpdate.setRoom(room);
        courseOfferingToUpdate.setCourse(course);
        courseOfferingToUpdate.setFaculty(faculty);
        courseOfferingToUpdate.setAuditData(auditData);


        courseOfferingRepository.save(courseOfferingToUpdate);


        return CourseOfferingDetailsDTO.builder()
                .courseOfferingType(courseOfferingType)
                .courseId(courseId)
                .facultyId(facultyId)
                .capacity(capacity)
                .room(room)
                .build();
    }

    @Override
    public void deleteCourseOffering(long id) {
        courseOfferingRepository.deleteById(id);
    }

    @Override
    public CourseOfferingDetailsDTO getCourseOfferingByID(long courseofferingId) {
        return courseOfferingRepository.getCourseOfferingDTOById(courseofferingId);
    }


}
