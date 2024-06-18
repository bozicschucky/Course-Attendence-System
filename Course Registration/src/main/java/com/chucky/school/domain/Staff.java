package com.chucky.school.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
public  class Staff extends Person{
    String role;

    public Staff(String firstName, String lastName, GenderType genderType, String emailAddress, LocalDate dateOfBirth, AuditData createdRecord, String username, String password, String role) {
        super(firstName, lastName, genderType, emailAddress, dateOfBirth, createdRecord, username, password);
        this.role = role;
    }
    public Staff() {}
}
