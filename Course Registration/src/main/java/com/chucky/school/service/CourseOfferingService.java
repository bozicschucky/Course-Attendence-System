package com.chucky.school.service;

import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.CourseOffering;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface CourseOfferingService {
      CourseOffering createCourseOffering( String courseOfferingType, long capacity, String room, AuditData auditData,long courseId, long facultyId );
      List<CourseOffering> getAllCoursOffering();

      CourseOffering updateCourseOffering(long id, CourseOffering courseOffering);

      void deleteCourseOffering(long id);

        CourseOffering getCourseOfferingByID(long courseId);


}
