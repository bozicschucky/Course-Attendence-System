package com.chucky.school.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Student extends Person {
  private long id;
  @Column(unique = true)
  private long studentId;
  private String Entry;
  private long alternateId;
  private long applicantId;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="facultyAdvisorId")
  private Faculty facultyAdvisorId;

  public Student(String firstName, String lastName, GenderType genderType, String emailAddress, LocalDate dateOfBirth, AuditData createdRecord, String username, String password, long studentId, String entry, long alternateId, long applicantId, Faculty facultyAdvisorId) {
    super(firstName, lastName, genderType, emailAddress, dateOfBirth, createdRecord, username, password);
    this.studentId = studentId;
    Entry = entry;
    this.alternateId = alternateId;
    this.applicantId = applicantId;
    this.facultyAdvisorId = facultyAdvisorId;
  }
  public Student () {}

}
