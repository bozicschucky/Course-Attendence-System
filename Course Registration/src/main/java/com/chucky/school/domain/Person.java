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
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SecondaryTable(name = "PersonAccount", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode
public abstract class Person {
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

  public Person(String firstName, String lastName, GenderType genderType, String emailAddress, LocalDate dateOfBirth, AuditData createdRecord, String username, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.genderType = genderType;
    this.emailAddress = emailAddress;
    this.dateOfBirth = dateOfBirth;
    this.createdRecord = createdRecord;
    this.username = username;
    this.password = password;
  }

}
