package com.chucky.school.service;

import com.chucky.school.DTO.CourseOfferingDetailsDTO;
import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.CourseOffering;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface CourseOfferingService {
      CourseOfferingDetailsDTO createCourseOffering( String courseOfferingType, long capacity, String room, AuditData auditData,long courseId, long facultyId );
      List<CourseOffering> getAllCoursOffering();
      CourseOfferingDetailsDTO updateCourseOffering(long id,String courseOfferingType, long capacity, String room, String updatedBy, long courseId, long facultyId );;
      void deleteCourseOffering(long id);
        CourseOfferingDetailsDTO getCourseOfferingByID(long courseId);
      List<CourseOfferingDetailsDTO> getCourseOfferingDetails();


}
