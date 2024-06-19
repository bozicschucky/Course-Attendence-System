package com.chucky.school.service;

import com.chucky.school.domain.Session;

public interface SessionService {
  Session createSession(Session session);

  Session deleteSession(Long id);

  Session updateSession(Long id, Session session);

}
