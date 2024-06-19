package com.chucky.school.domain;

import jakarta.persistence.*;
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
    @ManyToOne
    private CourseOffering courseOfferingId;
    @OrderBy("studentId ASC")
    @ManyToOne
    private Student studentId;
    private char grade;

}
