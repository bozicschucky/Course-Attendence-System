package com.chucky.school.repository;

import com.chucky.school.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findBySessionDate(LocalDate sessionDate);
}
