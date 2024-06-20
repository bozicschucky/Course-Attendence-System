package com.chucky.school.controller;

import com.chucky.school.Adaptor.SessionDTO;
import com.chucky.school.domain.Session;
import com.chucky.school.domain.sessionTime;
import com.chucky.school.service.SessionService;
import com.chucky.school.service.SessionServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Tag(name = "Sessions", description = "The Session API")
@RestController
@RequestMapping("/student-view/Sessions")
public class SessionController {

    @Autowired
    private SessionServiceImpl sessionService;

    @PostMapping
    public ResponseEntity<?> createSession(LocalDate sessionDate, String sessionTitle, sessionTime sessionStringTime,long courseOfferingId){

        Session session = sessionService.createSession(sessionDate, sessionTitle, sessionStringTime,courseOfferingId);
        SessionDTO sessionDTO1 = new SessionDTO();
        sessionDTO1.setId(session.getId());
        sessionDTO1.setDate(session.getSessionDate());
        sessionDTO1.setSessionTitle(session.getSessionTitle());
        return ResponseEntity.ok(sessionDTO1);
    }

    @GetMapping("/id")
    public ResponseEntity<?> getSession(long id) {
        Session session = sessionService.getSession(id);
        if (session == null) {
            return ResponseEntity.notFound().build();
        }
        SessionDTO sessionDTO1 = new SessionDTO();
        sessionDTO1.setId(session.getId());
        sessionDTO1.setDate(session.getSessionDate());
        sessionDTO1.setSessionTitle(session.getSessionTitle());


        return ResponseEntity.ok(sessionDTO1);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSessions() {
        Iterable<Session> sessions = sessionService.getAllSessions();
        Collection<SessionDTO> sessionDTO = new ArrayList<>();
        SessionDTO sessionDTO1 = new SessionDTO();
        for(Session session : sessions){
            sessionDTO1.setId(session.getId());
            sessionDTO1.setDate(session.getSessionDate());
            sessionDTO1.setSessionTitle(session.getSessionTitle());
            sessionDTO.add(sessionDTO1);
        }
        return ResponseEntity.ok(sessionDTO);
    }

}
