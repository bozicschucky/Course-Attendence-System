package com.chucky.school.Adaptor;


import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.GenderType;
import com.chucky.school.domain.Person;

import java.time.LocalDate;

public class StudentDTO extends Person {
    private long studentId;
    private String entry;
    private long alternateId;
    private long applicantId;
    private FacultyDTO facultyAdvisor;

    public StudentDTO(String firstName, String lastName, GenderType genderType, String emailAddress, LocalDate dateOfBirth, AuditData createdRecord, String username, String password, long studentId, String entry, long alternateId, long applicantId, FacultyDTO facultyAdvisor) {
        super(firstName, lastName, genderType, emailAddress, dateOfBirth, createdRecord, username, password);
        this.studentId = studentId;
        this.entry = entry;
        this.alternateId = alternateId;
        this.applicantId = applicantId;
        this.facultyAdvisor = facultyAdvisor;
    }
    public StudentDTO() {}


    // Getters and Setters
    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public long getAlternateId() {
        return alternateId;
    }

    public void setAlternateId(long alternateId) {
        this.alternateId = alternateId;
    }

    public long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }

    public FacultyDTO getFacultyAdvisor() {
        return facultyAdvisor;
    }

    public void setFacultyAdvisor(FacultyDTO facultyAdvisor) {
        this.facultyAdvisor = facultyAdvisor;
    }
}
