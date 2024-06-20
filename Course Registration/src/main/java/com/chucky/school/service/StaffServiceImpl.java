package com.chucky.school.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chucky.school.domain.Staff;
import com.chucky.school.repository.StaffRepository;

@Service
public class StaffServiceImpl implements StaffService {

  @Autowired
  private StaffRepository staffRepository;

  public StaffServiceImpl(StaffRepository staffRepository) {
    this.staffRepository = staffRepository;
  }

  @Override
  public void saveStaff(Staff staff) {
    staffRepository.save(staff);
  }

  @Override
  public Staff getStaff(String username) {
    return staffRepository.findByUsername(username);
  }

  @Override
  public void deleteStaff(String username) {
    Staff staff = staffRepository.findByUsername(username);
    staffRepository.delete(staff);
  }

  @Override
  public void updateStaff(String username, Staff staff) {
    Staff staffToUpdate = staffRepository.findByUsername(username);
    staffToUpdate.setFirstName(staff.getFirstName());
    staffToUpdate.setLastName(staff.getLastName());
    staffToUpdate.setEmailAddress(staff.getEmailAddress());
    staffToUpdate.setRole(staff.getRole());
    staffToUpdate.setUsername(staff.getUsername());
    staffToUpdate.setPassword(staff.getPassword());
    staffRepository.save(staffToUpdate);
  }

  public List<Staff> getAllStaff() {
    return staffRepository.findAll();
  }

}
