package com.chucky.school.service;

import com.chucky.school.DTO.CourseRegistrationDTO;
import com.chucky.school.domain.CourseOffering;
import com.chucky.school.domain.CourseRegistration;

import java.util.List;
import java.util.Optional;


public interface CourseRegistrationService {
 public CourseRegistration createRegistration(long courseOfferingId,long studentId);
 CourseRegistrationDTO findRegistrationById(long registrationId);
 public CourseRegistration updateRegistration(long registrationId,long courseOfferingId, long studentId,char grade);
 public List<CourseRegistrationDTO> findAllRegistrations();
 public void deleteRegistration(long registrationId);
 public List<Object[]> getAllFromCourseOffering(long courseOfferingId);
List<Object[]> getAllFromStudent(long studentId);
}
