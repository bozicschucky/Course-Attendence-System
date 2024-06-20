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
    @JoinColumn(name ="course_offering_id")
    private CourseOffering courseOfferingId;

    @ManyToOne
    @JoinColumn  (name ="student_id")
    private Student studentId;
    private char grade;

}
