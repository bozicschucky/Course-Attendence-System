package com.chucky.school.service;

import com.chucky.school.domain.AttendanceRecord;
import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.Location;
import com.chucky.school.domain.LocationType;
import com.chucky.school.repository.AttendanceRecordRepository;
import com.chucky.school.repository.LocationRepository;
import com.chucky.school.repository.LocationTypeRepository;
import com.chucky.school.service.AttendanceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository LocationRepository;
    @Autowired
    private LocationTypeRepository locationTypeRepository;

    @Autowired
    private AttendanceServiceImplm attendanceService;

    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;



    //create location
    public Location createLocation(long typeId, long capacity, String name,String createby, String locationType) {
        AuditData createdRecord = new AuditData(createby);
        LocationType locationType1 = new LocationType(locationType, createdRecord);
        locationTypeRepository.save(locationType1);

        Location location = new Location(typeId, capacity, name, createdRecord, locationType1);
        LocationRepository.save(location);
        return location;
    }

    //get location by id

    public Location getLocation(long id) {
        Optional<Location> location =  LocationRepository.findById(id);
        if(location.isEmpty()){
            throw new EntityNotFoundException("Loction Record with ID " + id + " not found");
        }
            return LocationRepository.findById(id).get();
    }


    //get all locations

    public Collection<Location> getAllLocations() {
        return LocationRepository.findAll();
    }

    public void deleteLocation(long id) {
        Collection<AttendanceRecord> attendanceRecords = attendanceRecordRepository.findAllByLocationId(id);
        Optional<Location> location = LocationRepository.findById(id);

        if(location.isEmpty()){
            throw new EntityNotFoundException("Location Record with ID " + id + " not found");
        }
        for (AttendanceRecord attendanceRecord : attendanceRecords){
            attendanceService.deleteAttendanceRecord(attendanceRecord.getId());
        }
        //attendanceRecordRepository.deleteByStudentId(id);  // Delete related attendance records first
            LocationRepository.delete(location.get());
    }


    //update location by id

    public Location updateLocation(long id, Location locationDTO){

        Optional<Location> location1 = LocationRepository.findById(id);
        if(location1.isEmpty()){
            throw new EntityNotFoundException("Location Record with ID " + id + " not found");
        }
            Location updatedLocation = location1.get();
            updatedLocation.setTypeId(locationDTO.getTypeId());
            updatedLocation.setCapacity(locationDTO.getCapacity());
            updatedLocation.setName(locationDTO.getName());
            updatedLocation.setCreatedRecord(locationDTO.getCreatedRecord());
            updatedLocation.setLocationType(updatedLocation.getLocationType());
            LocationRepository.save(updatedLocation);

            return LocationRepository.findById(id).get();

    }

   public void deleted(){
        LocationRepository.deleteAll();
   }

}
