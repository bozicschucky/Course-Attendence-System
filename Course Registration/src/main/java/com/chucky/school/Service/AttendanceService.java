package com.chucky.school.service;

import com.chucky.school.DTO.AttendanceRecordDTO;
import com.chucky.school.DTO.SessionDTO;
import com.chucky.school.domain.AttendanceRecord;

import java.time.LocalDateTime;
import java.util.Collection;

public interface AttendanceService {
    Collection<SessionDTO> getAllSessions();
    public AttendanceRecordDTO createAttendanceRecord(long sessionID,long studentId, long locationId);
    AttendanceRecordDTO getAttendance(long id);
    Collection<AttendanceRecordDTO> getAllAttendanceRecords();
    void deleteAttendanceRecord(long id);
    AttendanceRecordDTO updateAttendanceRecord(long id, long studentId, long locationId);
}
