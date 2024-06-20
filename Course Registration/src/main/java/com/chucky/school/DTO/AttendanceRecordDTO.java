package com.chucky.school.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendanceRecordDTO {
    private long id;
    private LocalDateTime scanDateTime;
    private long studentId;
    private String firstName;
    private String lastName;
    private LocationDTO locationDTO;


}
