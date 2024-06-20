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

//    @Query("SELECT ar FROM AttendanceRecord ar WHERE ar.student.id = :studentId AND ar.location.id = :locationId AND ar.scanDateTime BETWEEN :startDateTime AND :endDateTime")
//    Optional<AttendanceRecord> findExistingRecord(@Param("studentId") Long studentId, @Param("locationId") Long locationId, @Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);

    List<AttendanceRecord> findBySession_SessionDateAndStudent_Id(LocalDate sessionDate, Long studentId);

    Collection<AttendanceRecord> findAllByLocationId(long locationId);
}
