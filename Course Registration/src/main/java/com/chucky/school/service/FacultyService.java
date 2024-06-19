package com.chucky.school.service;

import com.chucky.school.Adaptor.FacultyDTO;

public interface FacultyService {
  String getFacultyName();

  FacultyDTO createFaculty(FacultyDTO facultyDTO);
   
}
