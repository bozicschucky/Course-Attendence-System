package com.chucky.school.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "course_prerequisite")
public class CoursePrerequisite {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer prerequisiteId;

  @ManyToMany(mappedBy = "prerequisites")
  private List<Course> courses;

}
