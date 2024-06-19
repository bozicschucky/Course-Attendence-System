package com.chucky.school.service;

import com.chucky.school.domain.CourseRegistration;

import java.util.List;
import java.util.Optional;


public interface CourseRegistrationService {
 public CourseRegistration createRegistration(long courseOfferingId,long studentId);
 public Optional<CourseRegistration> findRegistrationById(long registrationId);
 public CourseRegistration updateRegistration(long registrationId,long courseOfferingId, long studentId,char grade);
 List<CourseRegistration> findAllRegistrations();
 public void deleteRegistration(long registrationId);

}
