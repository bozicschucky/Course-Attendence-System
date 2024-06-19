package com.chucky.school.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OrderBy;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRegistration {
    @Id
    @GeneratedValue
    private long id;
    private long courseOfferingId;
    @OrderBy("studentId ASC")
    private long studentId;
    private char grade;

}
