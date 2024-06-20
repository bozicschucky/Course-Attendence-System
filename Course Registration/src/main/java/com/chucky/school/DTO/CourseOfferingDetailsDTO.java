package com.chucky.school.DTO;

import com.chucky.school.domain.CourseOffering;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public CourseOfferingDetailsDTO(long id, long courseId, long facultyId, long capacity, String courseOfferingType, String room) {
        this.id = id;
        this.courseId = courseId;
        this.facultyId = facultyId;
        this.capacity = capacity;
        this.courseOfferingType = courseOfferingType;
        this.room = room;
    }




}
