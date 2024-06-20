package com.chucky.school.Adaptor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;


@Data
@NoArgsConstructor
public class CoursseOfferingSessionDTO {
    private long id;
    private long courseId;
    private long facultyId;
    private long capacity;
    private String courseOfferingType;
    private String room;
    private LocalDate startDate;
    private LocalDate endDate;
    private Collection<SessionDTO> session = new ArrayList<SessionDTO>();



    public CoursseOfferingSessionDTO(long id, long courseId, long facultyId, long capacity, String courseOfferingType, String room, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.courseId = courseId;
        this.facultyId = facultyId;
        this.capacity = capacity;
        this.courseOfferingType = courseOfferingType;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }




}
