package com.chucky.school.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chucky.school.domain.Session;
import com.chucky.school.service.SessionServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Sessions", description = "The Session API")
@RestController
public class SessionController {

  @Autowired
  SessionServiceImpl sessionService;

  @PostMapping("/sys-admin/sessions")
  public ResponseEntity<?> createSession(

      @RequestParam(value = "sessionTitle", required = true) String sessionTitle,
      @RequestParam(value = "sessionType", required = true) String sessionType) {

    Session session = new Session(
        sessionTitle,
        sessionType);

    sessionService.createSession(session);
    return ResponseEntity.ok().body(Map.of(
        "message", "Session created successfully",
        "session", session));
  }

  @DeleteMapping("/sys-admin/sessions")
  public ResponseEntity<?> deleteSession(
      @RequestParam(value = "id", required = true) Long id) {

    Session session = sessionService.deleteSession(id);
    return ResponseEntity.ok().body(Map.of(
        "message", "Session deleted successfully",
        "session", session));
  }

  @PutMapping("/sys-admin/sessions/{id}")
  public ResponseEntity<?> updateSession(
      @RequestParam(value = "id", required = true) Long id,
      @RequestParam(value = "sessionTitle", required = true) String sessionTitle,
      @RequestParam(value = "sessionType", required = true) String sessionTypeString) {

    Session session = new Session(
        sessionTitle,
        sessionTypeString);

    sessionService.updateSession(id, session);
    return ResponseEntity.ok().body(Map.of(
        "message", "Session updated successfully",
        "session", session));
  }

}
