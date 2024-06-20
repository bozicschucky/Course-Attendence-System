package com.chucky.school.domain;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Session {

    @Id
    @GeneratedValue
    private Long id;


    private LocalDate sessionDate;
    private String sessionTitle;

    @Enumerated(EnumType.STRING)
    private sessionTime sessionTime;

    @ManyToOne(cascade = CascadeType.ALL)
    private CourseOffering courseOffering;


}
