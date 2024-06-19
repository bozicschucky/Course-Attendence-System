package com.chucky.school.domain;

import java.time.LocalDateTime;
import jakarta.persistence.*;

import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AttendanceRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private LocalDateTime scanDateTime;
  @ManyToOne(cascade = CascadeType.ALL)
  public Student student;

  private DayTime dayTime;

  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "location_id")
  Location location;

  public AttendanceRecord(LocalDateTime scanDateTime, Student student, DayTime dayTime, Location location
      ) {
    this.scanDateTime = scanDateTime;
    this.student = student;
    this.dayTime = dayTime;
    this.location = location;
  }
}
