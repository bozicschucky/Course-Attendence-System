package com.chucky.school.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chucky.school.domain.Staff;
import com.chucky.school.repository.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private StaffRepository staffRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Staff user = staffRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }

    return User.withUsername(user.getUsername())
        .password(user.getPassword())
        .roles(user.getRole())
        .build();
  }
}