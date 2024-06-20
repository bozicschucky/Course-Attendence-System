package com.chucky.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.chucky.school.DTO.LoginDto;
import com.chucky.school.security.JwtTokenProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider JwtTokenProvider;

  @Override
  public String login(LoginDto loginDto) {

    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        loginDto.getUserName(),
        loginDto.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = JwtTokenProvider.generateToken(authentication);

    return token;
  }
}