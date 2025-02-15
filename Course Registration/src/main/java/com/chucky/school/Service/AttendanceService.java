package com.chucky.school.service;

import com.chucky.school.Adaptor.AttendanceRecordDTO;
import com.chucky.school.domain.AttendanceRecord;

public interface AttendanceService {
    AttendanceRecord createAttendanceRecord(long studentId, long locationId);
    AttendanceRecord updateAttendanceRecord(long id, AttendanceRecord attendanceRecord);
    AttendanceRecordDTO getAttendanceRecord(long id);
    void deleteAttendanceRecord(long id);

}
