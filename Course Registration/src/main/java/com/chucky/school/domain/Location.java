package com.chucky.school.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString

public class Location {
  @Id
  @GeneratedValue
  private long id;
  private long typeId;
  private long capacity;
  private String name;
  @Embedded
  private AuditData createdRecord;
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private LocationType locationType;

  public Location(long typeId, long capacity, String name,
                  AuditData createdRecord, LocationType locationType) {
    this.typeId = typeId;
    this.capacity = capacity;
    this.name = name;
    this.createdRecord = createdRecord;
    this.locationType = locationType;
  }
}
