package com.chucky.school.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Person {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "student_id")
  private long id;
  private long studentId;
  private String Entry;
  private long alternateId;
  private long applicantId;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinTable(name ="student_faculty_advisor", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "faculty_id"))
  private Faculty facultyAdvisorId;
  @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
  private List<AttendanceRecord> attendanceRecord;
}
