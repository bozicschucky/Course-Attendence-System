package com.chucky.school.service;

import java.util.List;

import com.chucky.school.domain.Staff;

public interface StaffService {

  public void saveStaff(Staff staff);

  public Staff getStaff(String username);

  public void deleteStaff(String username);

  public void updateStaff(String username, Staff staff);

  public List<Staff> getAllStaff();

}
