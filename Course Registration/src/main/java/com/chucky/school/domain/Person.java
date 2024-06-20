package com.chucky.school.domain;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SecondaryTable(name = "PersonAccount", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private long id;
  private String firstName;
  private String lastName;
  @Enumerated(EnumType.STRING)
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
