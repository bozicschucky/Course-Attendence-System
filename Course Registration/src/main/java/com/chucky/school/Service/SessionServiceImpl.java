package com.chucky.school.service;

import com.chucky.school.domain.CourseOffering;
import com.chucky.school.domain.Session;
import com.chucky.school.domain.sessionTime;
import com.chucky.school.repository.CourseOfferingRepository;
import com.chucky.school.repository.SessionRepository;
import exception.ResourceNotFoundException;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService{

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private CourseOfferingRepository courseOfferingRepository;

    public Session createSession(LocalDate sessionDate, String sessionTitle, sessionTime sessionStringTime,long courseOfferingId) {

        Optional<CourseOffering> courseOffering = courseOfferingRepository.findById(courseOfferingId);
        if (courseOffering.isEmpty()) {
            throw new ResourceNotFoundException("Unable to find course offering with id " + courseOfferingId);
        }

        Session session = new Session();
        session.setSessionDate(sessionDate);
        session.setSessionTitle(sessionTitle);
        session.setSessionTime(sessionStringTime);
        session.setCourseOffering(courseOffering.get());
         sessionRepository.save(session);
        courseOffering.get().addSession(session);
        return session;
    }

    public Session getSession(long id) {
        return sessionRepository.findById(id).get();
    }

    public Iterable<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public void deleteSession(long id) {
        sessionRepository.deleteById(id);
    }
    public Session updateSession(long id, Session session) {
        Session session1 = sessionRepository.findById(id).get();
        session1.setSessionDate(session.getSessionDate());
        session1.setSessionTitle(session.getSessionTitle());
        session1.setSessionTime(session.getSessionTime());
        sessionRepository.save(session1);
        return session1;
    }
}
