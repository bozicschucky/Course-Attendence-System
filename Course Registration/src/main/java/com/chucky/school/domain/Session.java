package com.chucky.school.domain;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Session {

    @Id
    @GeneratedValue
    private Long id;


    private LocalDate sessionDate;
    private String sessionTitle;
    private LocalTime startTime;
    private LocalTime endTime;

    @OneToMany( cascade = CascadeType.ALL)
    private List<AttendanceRecord> attendanceRecords = new ArrayList<>();


    public void addAttendanceRecord(AttendanceRecord attendanceRecord) {
        attendanceRecords.add(attendanceRecord);

    }


    public void removeAttendanceRecord(AttendanceRecord attendanceRecord) {
        attendanceRecords.remove(attendanceRecord);
    }
}
