package com.chucky.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class AdminController {

  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
  @GetMapping("/admin")
  public ResponseEntity<String> addAmin() {
    return ResponseEntity.ok("Adding Admin");
  }

  @PreAuthorize("hasRole('USER')")
  @GetMapping("/user")
  public ResponseEntity<String> addUser() {
    return ResponseEntity.ok("Adding User");
  }
}