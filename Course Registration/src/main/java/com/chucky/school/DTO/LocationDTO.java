package com.chucky.school.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationDTO {
    private long typeId;
    private long capacity;
    private String name;
    private String locationType;

}
