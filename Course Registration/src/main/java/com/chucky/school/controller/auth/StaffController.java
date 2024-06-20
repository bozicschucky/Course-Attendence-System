package com.chucky.school.controller.auth;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.GenderType;
import com.chucky.school.domain.Staff;
import com.chucky.school.service.StaffService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Staff", description = "The Staff API")
@RestController
@RequestMapping("/api/staff/")
public class StaffController {

  @Autowired
  StaffService staffService;

  @GetMapping("/all-staff")
  public ResponseEntity<?> getAllStaff() {
    return ResponseEntity.ok(staffService.getAllStaff());

  }

  @PostMapping("/add-staff")
  public ResponseEntity<?> addUser(
      @RequestParam(value = "firstName", required = true) String firstName,
      @RequestParam(value = "lastName", required = true) String lastName,
      @RequestParam(value = "emailAddress", required = true) String emailAddress,
      @RequestParam(value = "role", required = true) String role,
      @RequestParam(value = "genderType", required = true) String genderType,
      @RequestParam(value = "dateOfBirth", required = true) LocalDate dateOfBirth,
      @RequestParam(value = "createdBy", required = true) String createdBy,
      @RequestParam(value = "username", required = true) String username,
      @RequestParam(value = "password", required = true) String password) {

    GenderType staffGenderType;

    if (genderType.toUpperCase().equals("MALE")) {
      staffGenderType = GenderType.MALE;
    } else if (genderType.toUpperCase().equals("FEMALE")) {
      staffGenderType = GenderType.FEMALE;
    } else {
      staffGenderType = GenderType.OTHER;
    }

    // BCrpyt the password
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashedPassword = passwordEncoder.encode(password);

    AuditData createdRecord = new AuditData(createdBy);

    staffService.saveStaff(new Staff(firstName, lastName, staffGenderType, emailAddress, dateOfBirth, createdRecord,
        username, hashedPassword, role.toUpperCase()));

    Staff staff = staffService.getStaff(username);
    return ResponseEntity.ok().body(Map.of(
        "message", "Staff added successfully",
        "staff username", staff.getUsername(),
        "role", staff.getRole(),
        "email", staff.getEmailAddress(),
        "gender", staff.getGenderType(),
        "date of birth", staff.getDateOfBirth()));
  }

  @GetMapping("/get-staff/{username}")
  public ResponseEntity<?> getMethodName(@PathVariable(value = "username", required = true) String username) {
    Staff staff = staffService.getStaff(username);
    return ResponseEntity.ok().body(Map.of(
        "message", "Staff added successfully",
        "staff username", staff.getUsername(),
        "role", staff.getRole(),
        "email", staff.getEmailAddress(),
        "gender", staff.getGenderType(),
        "date of birth", staff.getDateOfBirth()));
  }

}
