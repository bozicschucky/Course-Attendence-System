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

}
