package com.chucky.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chucky.school.domain.Faculty;
import com.chucky.school.repository.FacultyRepository;

@Service
public class FacultyService {
  @Autowired
  private FacultyRepository facultyRepository;

  public Faculty getFacultyById(Long id) {
    return facultyRepository.findById(id).get();
  }
}
