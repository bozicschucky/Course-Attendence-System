package com.chucky.school.repository;

import com.chucky.school.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findBySessionDate(LocalDate sessionDate);
    Optional<Session> findById(Long id);
}
