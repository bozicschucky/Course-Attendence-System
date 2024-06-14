package com.chucky.school.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SecondaryTable(name = "PersonAccount", pkJoinColumns = @PrimaryKeyJoinColumn(name = "person_id"))
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
  @Column(name = "person_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private long id;
  private String firstName;
  private String lastName;
  private GenderType genderType;
  private String emailAddress;
  private LocalDate dateOfBirth;
  @Embedded
  private AuditData createdRecord;
  @Column(table = "PersonAccount")
  private String username;
  @Column(table = "PersonAccount")
  private String password;
}
