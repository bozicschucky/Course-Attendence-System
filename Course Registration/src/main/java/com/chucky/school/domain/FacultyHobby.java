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
  private long faculty_id;
  private List<String> hobbies;

  FacultyHobby() {
    hobbies = new ArrayList<>();
  }


  public void addHobby(String hobby) {
    hobbies.add(hobby);
  }

}
