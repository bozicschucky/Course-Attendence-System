package com.chucky.school.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendanceRecordDTO {
    private long id;
    private long studentId;
    private String firstName;
    private String lastName;
    private LocationDTO locationDTO;

}
