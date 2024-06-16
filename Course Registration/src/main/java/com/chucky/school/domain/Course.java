package com.chucky.school.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private long credits;
  private String courseName;
  private String courseCode;
  private String courseDescription;
  private String department;
  @Embedded
  private AuditData createdRecord;

  public Course(long credits, String courseName, String courseCode, String courseDescription, String department,
      CreatedRecord createdRecord) {
    this.credits = credits;
    this.courseName = courseName;
    this.courseCode = courseCode;
    this.courseDescription = courseDescription;
    this.department = department;
    this.createdRecord = createdRecord;
  }

  @ManyToMany
  @JoinTable(
          name = "course_prerequisite",
          joinColumns = @JoinColumn(name = "course_id"),
          inverseJoinColumns = @JoinColumn(name = "prerequisite_id")
  )
  private Set<Course> prerequisites = new HashSet<>();

  @ManyToMany(mappedBy = "prerequisites")
  private Set<Course> courses = new HashSet<>();


  @OneToMany(mappedBy = "course")
  private List<CourseOffering> courseOfferings;
}