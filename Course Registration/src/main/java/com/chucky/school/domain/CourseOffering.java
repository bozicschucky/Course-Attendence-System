package com.chucky.school.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseOffering {
  @Id
  @GeneratedValue
  private long id;
  private long capacity;
  private String courseOfferingType;
  private String room;
  @Embedded
  private AuditData auditData;

  @ManyToOne
  private Course course;
  @ManyToOne
  private Faculty faculty;


}
