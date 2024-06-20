package com.chucky.school.Adaptor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CourseRegistrationDTO {
    private long id;
    private long courseOfferingId;
    private long studentId;
    private char grade;

}
