package com.chucky.school.Adaptor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StudentCourseOfferingDTO {
    private long courseOfferingId;
    private char grade ;
    private String courseName;
    private String courseCode;
    private String courseDescription;
}
