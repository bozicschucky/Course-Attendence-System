package com.chucky.school.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegistration {
  @Id
  @GeneratedValue
  private long id;
  private long courseOfferingId;
  @OrderBy("studentId ASC")
  private long studentId;

}
