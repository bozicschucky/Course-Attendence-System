package com.chucky.school.service;

import com.chucky.school.domain.Session;
import com.chucky.school.domain.sessionTime;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


public interface SessionService {
    Session createSession(LocalDate sessionDate, String sessionTitle, sessionTime sessionStringTime, long courseOfferingId);
    Session getSession(long id);
    Iterable<Session> getAllSessions();
    void deleteSession(long id);
    Session updateSession(long id, Session session);
}
