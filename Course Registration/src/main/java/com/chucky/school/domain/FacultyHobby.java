package com.chucky.school.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Embeddable
public class FacultyHobby {
  private List<String> hobbies;
  public FacultyHobby() {
    hobbies = new ArrayList<>();
  }

  public FacultyHobby(String hobby) {
    hobbies = new ArrayList<>();
    hobbies.add(hobby);
  }

  public void addHobby(String hobby) {
    hobbies.add(hobby);
  }

}
