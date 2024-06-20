package com.chucky.school.Adaptor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AdminCourseOfferingDTO {
    private long courseOfferingId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String courseOfferingType;
    private String room;
    private String facultyName;

}
