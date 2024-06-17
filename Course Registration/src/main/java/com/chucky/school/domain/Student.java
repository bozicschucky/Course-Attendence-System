package com.chucky.school.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
  private long studentId;
  private String Entry;
  private long alternateId;
  private long applicantId;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="facultyAdvisorId")
  private Faculty facultyAdvisorId;

  public long getStudentId() {
    return studentId;
  }

  public void setStudentId(long studentId) {
    this.studentId = studentId;
  }

  public String getEntry() {
    return Entry;
  }

  public void setEntry(String entry) {
    Entry = entry;
  }

  public long getAlternateId() {
    return alternateId;
  }

  public void setAlternateId(long alternateId) {
    this.alternateId = alternateId;
  }

  public long getApplicantId() {
    return applicantId;
  }

  public void setApplicantId(long applicantId) {
    this.applicantId = applicantId;
  }

  public Faculty getFacultyAdvisorId() {
    return facultyAdvisorId;
  }

  public void setFacultyAdvisorId(Faculty facultyAdvisorId) {
    this.facultyAdvisorId = facultyAdvisorId;
  }
}
