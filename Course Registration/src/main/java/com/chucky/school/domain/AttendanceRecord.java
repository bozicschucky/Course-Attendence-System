package com.chucky.school.domain;

import java.time.LocalDateTime;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

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
  private long studentId;
  private LocalDateTime scanDateTime;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "student_attendance_record", joinColumns = @JoinColumn(name = "attendance_record_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
  public List<Student> student;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "location_id")
  Location location;

}
