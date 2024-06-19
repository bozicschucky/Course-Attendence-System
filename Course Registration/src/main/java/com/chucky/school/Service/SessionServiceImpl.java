package com.chucky.school.service;

import com.chucky.school.domain.Session;
import com.chucky.school.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {
  @Autowired
  private SessionRepository sessionRepository;

  public Session createSession(Session session) {
    return sessionRepository.save(session);
  }

  public Session deleteSession(Long id) {
    Session session = sessionRepository.findById(id).orElse(null);
    if (session != null) {
      sessionRepository.delete(session);
    }
    return session;
  }

  public Session updateSession(Long id, Session session) {
    Session existingSession = sessionRepository.findById(id).orElse(null);
    if (existingSession != null) {
      existingSession.setSessionDate(session.getSessionDate());
      existingSession.setSessionTitle(session.getSessionTitle());

      sessionRepository.save(existingSession);
    }
    return existingSession;
  }

}
