package com.chucky.school.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseOffering {
  @Id
  @GeneratedValue
  private long courseOfferingId;
  private long courseId;
  private long capacity;
  private String courseOfferingType;
  private String room;
  @Embedded
  private CreatedRecord createdRecord;

  @ManyToOne
  private Course course;

  @ManyToOne
  private Faculty faculty;

}
