package com.chucky.school.service;

import com.chucky.school.DTO.AttendanceRecordDTO;
import com.chucky.school.DTO.SessionDTO;
import com.chucky.school.domain.AttendanceRecord;

import java.time.LocalDateTime;
import java.util.Collection;

public interface AttendanceService {
    AttendanceRecord createAttendanceRecord(long studentId, long locationId);
    AttendanceRecord updateAttendanceRecord(long id, AttendanceRecord attendanceRecord);
    AttendanceRecordDTO getAttendanceRecord(long id);
    void deleteAttendanceRecord(long id);

}
