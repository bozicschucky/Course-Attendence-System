package com.chucky.school.Adaptor;


public class StudentDTO {
    private long studentId;
    private String entry;
    private long alternateId;
    private long applicantId;
    private FacultyDTO facultyAdvisor;

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
