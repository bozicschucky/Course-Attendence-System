package com.chucky.school.service;

import com.chucky.school.DTO.LoginDto;

public interface AuthService {
  String login(LoginDto loginDto);
}