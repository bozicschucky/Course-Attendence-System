package com.chucky.school.Adaptor;


import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.GenderType;
import com.chucky.school.domain.Person;
import com.chucky.school.domain.Staff;

import java.time.LocalDate;
import java.util.List;

public class FacultyDTO extends Staff {
    private long id;
    private String salutation;
    private List<String> hobbies; // Assuming hobbies can be represented as a list of strings

    public FacultyDTO(String firstName, String lastName, GenderType genderType, String emailAddress, LocalDate dateOfBirth, AuditData createdRecord, String username, String password, String role, String salutation, List<String> hobbies) {
        super(firstName, lastName, genderType, emailAddress, dateOfBirth, createdRecord, username, password, role);
        this.salutation = salutation;
        this.hobbies = hobbies;
    }
    public FacultyDTO() {}

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
}