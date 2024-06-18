package com.chucky.school.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.chucky.school.filters.JwtRequestFilter;
import com.chucky.school.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final MyUserDetailsService myUserDetailsService;
  private final JwtRequestFilter jwtRequestFilter;

  @Autowired
  public SecurityConfig(MyUserDetailsService myUserDetailsService, JwtRequestFilter jwtRequestFilter) {
    this.myUserDetailsService = myUserDetailsService;
    this.jwtRequestFilter = jwtRequestFilter;
  }

  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests().requestMatchers("/**").hasRole("USER").requestMatchers("/admin/**").hasRole("ADMIN");
    return http.build();
  }
}