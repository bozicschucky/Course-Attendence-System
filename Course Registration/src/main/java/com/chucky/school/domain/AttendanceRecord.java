package com.chucky.school.domain;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AttendanceRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private LocalDateTime scanDateTime;
  @OneToOne(cascade = CascadeType.ALL)
  public Student student;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "location_id")
  Location location;

  @ManyToOne
  @JoinColumn(name = "session_id")
  private Session session;

  public AttendanceRecord(LocalDateTime scanDateTime, Student student, Location location, Session session) {
    this.scanDateTime = scanDateTime;
    this.student = student;
    this.location = location;
    this.session = session;
  }
}
