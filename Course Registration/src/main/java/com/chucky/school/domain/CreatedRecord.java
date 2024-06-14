package com.chucky.school.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class CreatedRecord {
  private String createdOn;
  private String createdBy;
  private String updatedOn;
  private String updatedBy;

}
