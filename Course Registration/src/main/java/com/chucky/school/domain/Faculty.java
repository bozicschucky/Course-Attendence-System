package com.chucky.school.domain;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
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
  private long id;
  private String Salutation;
  @ElementCollection
  private List<FacultyHobby> hobbies;

  @OneToMany(mappedBy = "faculty")
  private List<CourseOffering> courseOfferings;

}
