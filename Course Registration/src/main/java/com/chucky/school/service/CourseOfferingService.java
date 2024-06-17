package com.chucky.school.service;

import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.CourseOffering;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface CourseOfferingService {
    public CourseOffering createCourseOffering( String courseOfferingType, long capacity, String room, AuditData auditData,long courseId, long facultyId );

    public CourseOffering getCourseOfferingByIdCourse(long courseOfferingId);

    public List<CourseOffering> getAllCoursOffering();

    public CourseOffering updateCourseOffering(long id, CourseOffering courseOffering);

    public void deleteCourseOffering(long id);

    public List<CourseOffering> getCourseOfferingByCourseID(long courseId);


}
