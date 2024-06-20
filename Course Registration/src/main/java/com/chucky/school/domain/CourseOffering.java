package com.chucky.school.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;


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

  @OneToMany(mappedBy = "courseOffering",cascade = CascadeType.ALL)
  private Collection<Session> session;

  @ManyToOne
  private Course course;
  @ManyToOne
  private Faculty faculty;
  private LocalDate startDate;
  private LocalDate endDate;

  public CourseOffering(long capacity, String courseOfferingType, String room, AuditData auditData, Collection<Session> session, Course course, Faculty faculty, LocalDate startDate, LocalDate endDate) {
    this.capacity = capacity;
    this.courseOfferingType = courseOfferingType;
    this.room = room;
    this.auditData = auditData;
    this.session = session;
    this.course = course;
    this.faculty = faculty;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public void addSession(Session session) {
    session.setCourseOffering(this);
    this.session.add(session);
  }

}
