package com.chucky.school.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessionDTO {
    private Long id;
    private String sessionTitle;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status;
}
