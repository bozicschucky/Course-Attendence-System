package com.chucky.school.domain;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDate sessionDate;
    private String sessionTitle;
    private LocalTime startTime;
    private LocalTime endTime;
    private String sessionType;


    public Session( String sessionTitle, String sessionType) {
        this.sessionDate = LocalDate.now();
        this.sessionTitle = sessionTitle;
        this.sessionType = sessionType;

        if (this.sessionType.toLowerCase() == "morning") {
            this.startTime = LocalTime.of(10, 0);
            this.endTime = LocalTime.of(12, 30);
        } else if (this.sessionType.toLowerCase() == "afternoon") {
            this.startTime = LocalTime.of(13, 30);
            this.endTime = LocalTime.of(16, 30);
        }
    }

}
