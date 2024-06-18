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
  private String Salutation;
  @ElementCollection
  private List<FacultyHobby> hobbies;

  @OneToMany(mappedBy = "faculty")
  private List<CourseOffering> courseOfferings;

  public Faculty(String firstName, String lastName, GenderType genderType, String emailAddress, LocalDate dateOfBirth, AuditData createdRecord, String username, String password, String role, String salutation, List<FacultyHobby> hobbies, List<CourseOffering> courseOfferings) {
    super(firstName, lastName, genderType, emailAddress, dateOfBirth, createdRecord, username, password, role);
    Salutation = salutation;
    this.hobbies = hobbies;
    this.courseOfferings = courseOfferings;
  }

}
