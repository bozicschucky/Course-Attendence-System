package com.chucky.school.repository;

import com.chucky.school.domain.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
    void deleteByStudentId(long studentId);

    Optional<AttendanceRecord> findById(long id);
    void findByScanDateTime(LocalDateTime scanDateTime);
    Collection<AttendanceRecord> findAllByLocationId(long locationId);
}
