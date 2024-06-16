package com.chucky.school.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class AuditData {
  private LocalDateTime createdOn;
  private String createdBy;
  private LocalDateTime updatedOn;
  private String updatedBy;

  public AuditData() {
    this.createdOn = LocalDateTime.now();
    this.updatedOn = LocalDateTime.now();
  }

  public AuditData(String createdBy) {
    this.createdBy = createdBy;
    this.createdOn = LocalDateTime.now();
    this.updatedOn = LocalDateTime.now();
    this.updatedBy = createdBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
    this.updatedOn = LocalDateTime.now();
  }

}
