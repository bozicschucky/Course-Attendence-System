package com.chucky.school.service;

import com.chucky.school.Adaptor.CourseRegistrationDTO;
import com.chucky.school.domain.CourseRegistration;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


public interface CourseRegistrationService {
 public CourseRegistration createRegistration(long courseOfferingId,long studentId) throws JsonProcessingException;
 CourseRegistrationDTO findRegistrationById(long registrationId);
 public CourseRegistration updateRegistration(long registrationId,long courseOfferingId, long studentId,char grade);
 public List<CourseRegistrationDTO> findAllRegistrations();
 public void deleteRegistration(long registrationId);
 public List<Object[]> getAllFromCourseOffering(long courseOfferingId);
List<Object[]> getAllFromStudent(long studentId);
}
