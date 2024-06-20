package com.chucky.school.domain;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {
    private String  studentName;
    private String body;
    private String  emailaddress;
    private String  subject;
}
