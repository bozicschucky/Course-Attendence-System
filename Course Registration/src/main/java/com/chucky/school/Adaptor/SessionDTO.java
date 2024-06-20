package com.chucky.school.Adaptor;

import com.chucky.school.domain.sessionTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessionDTO {
    private Long id;
    private String sessionTitle;
    private LocalDate date;

}
