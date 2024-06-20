package com.chucky.school.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LocationType {
  @Id
  @GeneratedValue
  private long id;
  private String type;
  @Embedded
  private AuditData createdRecord;

  public LocationType(String type, AuditData createdRecord) {
    this.type = type;
    this.createdRecord = createdRecord;
  }
}
