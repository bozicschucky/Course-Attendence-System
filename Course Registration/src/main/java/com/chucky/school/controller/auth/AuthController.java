package com.chucky.school.controller.auth;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chucky.school.DTO.JwtAuthResponse;
import com.chucky.school.DTO.LoginDto;
import com.chucky.school.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private AuthService authService;

  // Build Login REST API
  @PostMapping("/login")
  public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
    String token = authService.login(loginDto);

    JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
    jwtAuthResponse.setAccessToken(token);

    return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
  }

  @PostMapping("/create/faculty")
  public String createFaculty(@RequestBody String entity) {
    // TODO: process POST request

    return entity;
  }

  @PostMapping("/create/student")
  public String createStudent(@RequestBody String entity) {
    // TODO: create a student
    return entity;
  }

  @PostMapping("/create/staff")
  public String createStaff(@RequestBody String entity) {
    // TODO: create a staff
    return entity;
  }

}