package com.chucky.school.Adaptor;


import java.util.List;

public class FacultyDTO {
    private long id;
    private String salutation;
    private List<String> hobbies; // Assuming hobbies can be represented as a list of strings

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