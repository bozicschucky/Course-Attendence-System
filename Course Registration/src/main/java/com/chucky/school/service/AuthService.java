package com.chucky.school.service;

import com.chucky.school.dto.LoginDto;

public interface AuthService {
  String login(LoginDto loginDto);
}