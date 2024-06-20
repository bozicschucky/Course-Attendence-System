package com.chucky.school.service;

import com.chucky.school.domain.AttendanceRecord;
import com.chucky.school.domain.AuditData;
import com.chucky.school.domain.Location;
import com.chucky.school.domain.LocationType;
import com.chucky.school.repository.AttendanceRecordRepository;
import com.chucky.school.repository.LocationRepository;
import com.chucky.school.repository.LocationTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LocationServiceImplTest {

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private LocationTypeRepository locationTypeRepository;

    @Mock
    private AttendanceServiceImplm attendanceService;

    @Mock
    private AttendanceRecordRepository attendanceRecordRepository;

    @InjectMocks
    private LocationServiceImpl locationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }




    @Test
    public void testGetLocation() {
        long id = 1L;
        Location location = new Location();
        when(locationRepository.findById(id)).thenReturn(Optional.of(location));

        Location foundLocation = locationService.getLocation(id);

        assertEquals(location, foundLocation);
    }

    @Test
    public void testGetLocationNotFound() {
        long id = 1L;
        when(locationRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> locationService.getLocation(id));
    }

    @Test
    public void testGetAllLocations() {
        List<Location> locations = Arrays.asList(new Location(), new Location());
        when(locationRepository.findAll()).thenReturn(locations);

        Collection<Location> foundLocations = locationService.getAllLocations();

        assertEquals(locations, foundLocations);
    }

    @Test
    public void testDeleteLocation() {
        long id = 1L;
        Location location = new Location();
        Collection<AttendanceRecord> attendanceRecords = Arrays.asList(new AttendanceRecord(), new AttendanceRecord());

        when(locationRepository.findById(id)).thenReturn(Optional.of(location));
        when(attendanceRecordRepository.findAllByLocationId(id)).thenReturn(attendanceRecords);

        locationService.deleteLocation(id);

        verify(attendanceService, times(2)).deleteAttendanceRecord(anyLong());
        verify(locationRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteLocationNotFound() {
        long id = 1L;
        when(locationRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> locationService.deleteLocation(id));
    }

    @Test
    public void testUpdateLocation() {
        long id = 1L;
        Location locationDTO = new Location(1L, 200L, "Updated Name", new AuditData("Admin"), new LocationType());
        Location existingLocation = new Location();
        when(locationRepository.findById(id)).thenReturn(Optional.of(existingLocation));
        when(locationRepository.save(any(Location.class))).thenReturn(existingLocation);

        Location updatedLocation = locationService.updateLocation(id, locationDTO);

        assertEquals(existingLocation, updatedLocation);
        verify(locationRepository, times(1)).save(any(Location.class));
    }

    @Test
    public void testUpdateLocationNotFound() {
        long id = 1L;
        Location locationDTO = new Location();
        when(locationRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> locationService.updateLocation(id, locationDTO));
    }

    @Test
    public void testDeleted() {
        doNothing().when(locationRepository).deleteAll();

        locationService.deleted();

        verify(locationRepository, times(1)).deleteAll();
    }


}
