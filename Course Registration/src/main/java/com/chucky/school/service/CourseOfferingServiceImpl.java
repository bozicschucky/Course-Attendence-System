package com.chucky.school.service;

import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.Course;
import com.chucky.school.domain.CourseOffering;
import com.chucky.school.domain.Faculty;
import com.chucky.school.repository.CourseOfferingRepository;
import com.chucky.school.repository.CourseRepository;
import com.chucky.school.repository.FacultyRepository;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    public CourseOffering createCourseOffering(String courseOfferingType, long capacity, String room, AuditData auditData, long courseId, long facultyId) {

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
        return courseOffering;
    }


    @Override
    public List<CourseOffering> getAllCoursOffering() {
        return courseOfferingRepository.findAll();
    }

    @Override
    public CourseOffering updateCourseOffering(long id, CourseOffering courseOffering) {
        CourseOffering courseOfferingToUpdate = courseOfferingRepository.getReferenceById(id);


        courseOfferingToUpdate.setId(courseOffering.getId());
        courseOfferingToUpdate.setCapacity(courseOffering.getCapacity());
        courseOfferingToUpdate.setCourse(courseOffering.getCourse());
        courseOfferingToUpdate.setCourseOfferingType(courseOffering.getCourseOfferingType());
        courseOfferingToUpdate.setRoom(courseOffering.getRoom());
        courseOfferingToUpdate.setAuditData(courseOffering.getAuditData()
        );
        courseOfferingToUpdate.setFaculty(courseOffering.getFaculty());
        courseOfferingRepository.save(courseOfferingToUpdate);
        return courseOffering;
    }

    @Override
    public void deleteCourseOffering(long id) {
        courseOfferingRepository.deleteById(id);
    }

    @Override
    public CourseOffering getCourseOfferingByID(long courseofferingId) {
        return courseOfferingRepository.getCourseOfferingById(courseofferingId);
    }


}
