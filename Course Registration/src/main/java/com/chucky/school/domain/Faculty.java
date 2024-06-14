package com.chucky.school.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Faculty extends Staff {
  @Column(name = "faculty_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private long id;
  private String Salutation;
  @ElementCollection
  private List<FacultyHobby> hobbies;

  @OneToMany(mappedBy = "faculty")
  private List<CourseOffering> courseOfferings;

}
