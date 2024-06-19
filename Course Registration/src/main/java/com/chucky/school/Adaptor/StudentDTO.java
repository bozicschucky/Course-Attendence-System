package com.chucky.school.Adaptor;


import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.GenderType;
import com.chucky.school.domain.Person;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;

import java.time.LocalDate;
@Getter
@Setter
public class StudentDTO extends Person  {

    private long id;
    @Unique
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

}
