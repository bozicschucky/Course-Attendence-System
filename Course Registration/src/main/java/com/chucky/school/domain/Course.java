package com.chucky.school.domain;

import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
  @Id
  @GeneratedValue
  private long courseId;
  private long credits;
  private String courseName;
  private String courseCode;
  private String courseDescription;
  private String department;
  @Embedded
  private CreatedRecord createdRecord;

  @ManyToMany
  @JoinTable(name = "course_reqs", joinColumns = @JoinColumn(name = "courseId"), inverseJoinColumns = @JoinColumn(name = "prerequisiteId"))
  private List<CoursePrerequisite> prerequisites;

  @OneToMany(mappedBy = "course")
  private List<CourseOffering> courseOfferings;
}