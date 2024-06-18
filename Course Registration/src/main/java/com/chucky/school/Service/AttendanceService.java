package com.chucky.school.service;

import com.chucky.school.DTO.AttendanceRecordDTO;
import com.chucky.school.domain.AttendanceRecord;

import java.time.LocalDateTime;
import java.util.Collection;

public interface AttendanceService {
    public AttendanceRecordDTO createAttendanceRecord(Long studentId, Long locationId);
    AttendanceRecordDTO getAttendance(Long id);
    Collection<AttendanceRecordDTO> getAllAttendanceRecords();
    void deleteAttendanceRecord(long id);
    AttendanceRecordDTO updateAttendanceRecord(long id, Long studentId, Long locationId);
}
