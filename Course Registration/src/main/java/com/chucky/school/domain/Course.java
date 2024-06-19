package com.chucky.school.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.*;

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
  @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @Column(name = "session_id")
  List<Session> currentSession = new ArrayList<>();
  @Embedded
  private AuditData createdRecord;

  public Course(long credits, String courseName, String courseCode, String courseDescription, String department,
      AuditData createdRecord) {
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

  public void addPrerequisite(Course course) {
    prerequisites.add(course);
    course.courses.add(this);
  }

  public void removePrerequisite(Course course) {
    prerequisites.remove(course);
    course.courses.remove(this);
  }

  public void addSession(Session session) {
    currentSession.add(session);
  }

  public List<Session> getSessions() {
    return currentSession;
  }
}