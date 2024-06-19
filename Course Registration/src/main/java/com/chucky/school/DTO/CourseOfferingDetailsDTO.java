package com.chucky.school.DTO;

import com.chucky.school.domain.CourseOffering;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
 @Builder
@NoArgsConstructor
public class CourseOfferingDetailsDTO {
    private long id;
    private long courseId;
    private long facultyId;
    private long capacity;
    private String courseOfferingType;
    private String room;
    private LocalDate startDate;
    private LocalDate endDate;

    public CourseOfferingDetailsDTO(long id, long courseId, long facultyId, long capacity, String courseOfferingType, String room, LocalDate startDate, LocalDate endDate) {
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
