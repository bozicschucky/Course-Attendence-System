package com.chucky.school.service;

import com.chucky.school.domain.Location;

public interface LocationService {
    Location createLocation(long typeId, long capacity, String name, String createby, String locationType);
    Location getLocation(long id);
    void deleteLocation(long id);
    Location updateLocation(long id, Location locationDTO);
    void deleted();
}
