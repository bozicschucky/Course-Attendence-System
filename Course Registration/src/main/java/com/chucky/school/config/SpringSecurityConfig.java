package com.chucky.school.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;

import com.chucky.school.security.JwtAuthenticationEntryPoint;
import com.chucky.school.security.JwtAuthenticationFilter;
import com.chucky.school.service.MyUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {
  @Autowired
  private final MyUserDetailsService myUserDetailsService;
  @Autowired
  private final JwtAuthenticationFilter jwtRequestFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests((authorize) -> {
          authorize.requestMatchers("/", "swagger-ui/index.html").permitAll();
          authorize
              .requestMatchers(
                  "/v3/api-docs/**")
              .permitAll();
          authorize.requestMatchers("/student-view/**").permitAll();
          authorize.requestMatchers("/api/auth/**").permitAll();
          authorize.requestMatchers("/sys-admin/**").hasAnyAuthority("SYS_ADMIN");
          authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
          authorize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());

    http.exceptionHandling(exception -> exception
        .authenticationEntryPoint(new JwtAuthenticationEntryPoint()));

    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

}