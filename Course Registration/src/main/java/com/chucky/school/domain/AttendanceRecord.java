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
  @GeneratedValue
  private long id;
  private LocalDateTime scanDateTime;
  @OneToOne(cascade = CascadeType.ALL)
  public Student student;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "location_id")
  Location location;
}
