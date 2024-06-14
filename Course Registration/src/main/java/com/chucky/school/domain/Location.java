package com.chucky.school.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
  @Id
  @GeneratedValue
  private long locationId;
  private long typeId;
  private long capacity;
  private String name;
  @Embedded
  private AuditData createdRecord;
  @ManyToOne
  private LocationType locationType;
}
