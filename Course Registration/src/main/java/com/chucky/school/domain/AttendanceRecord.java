package com.chucky.school.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

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



  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
  @JoinColumn(name = "location_id")
  private Location location;


  public AttendanceRecord(LocalDateTime scanDateTime, Student student, Location location) {
    this.scanDateTime = scanDateTime;
    this.student = student;
    this.location = location;
  }
}
