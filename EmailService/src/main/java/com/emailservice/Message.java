package com.emailservice;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Data
@Getter
@Setter
@ToString
public class Message  {
    private String  studentName;
    private String body;
    private String  emailaddress;
    private String  subject;
}
