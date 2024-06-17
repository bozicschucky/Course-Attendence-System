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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getSalutation() {
    return Salutation;
  }

  public void setSalutation(String salutation) {
    Salutation = salutation;
  }

  public List<FacultyHobby> getHobbies() {
    return hobbies;
  }

  public void setHobbies(List<FacultyHobby> hobbies) {
    this.hobbies = hobbies;
  }

  public List<CourseOffering> getCourseOfferings() {
    return courseOfferings;
  }

  public void setCourseOfferings(List<CourseOffering> courseOfferings) {
    this.courseOfferings = courseOfferings;
  }
}
