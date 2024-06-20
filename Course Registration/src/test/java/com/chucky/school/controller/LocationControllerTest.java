package com.chucky.school.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


import com.chucky.school.domain.Location;
import com.chucky.school.service.LocationServiceImpl;


public class LocationControllerTest {

    @Mock
    private LocationServiceImpl locationService;

    @InjectMocks
    private LocationController locationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateLocation() {
        long typeId = 1L;
        long capacity = 100L;
        String name = "Test Location";
        String createdBy = "Admin";
        String locationType = "Classroom";
        Location location = new Location();
        when(locationService.createLocation(typeId, capacity, name, createdBy, locationType)).thenReturn(location);

        ResponseEntity<?> responseEntity = locationController.createLocation(typeId, capacity, name, createdBy, locationType);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(location, responseEntity.getBody());
    }

    @Test
    public void testGetLocation() {
        long id = 1L;
        Location location = new Location();
        when(locationService.getLocation(id)).thenReturn(location);

        ResponseEntity<?> responseEntity = locationController.getLocation(id);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(location, responseEntity.getBody());
    }

    @Test
    public void testGetAllLocations() {
        Collection<Location> locations = Arrays.asList(new Location(), new Location());
        when(locationService.getAllLocations()).thenReturn(locations);

        ResponseEntity<?> responseEntity = locationController.getAllLocations();

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(locations, responseEntity.getBody());
    }

    @Test
    public void testDeleteLocation() {
        long id = 1L;
        doNothing().when(locationService).deleteLocation(id);

        ResponseEntity<?> responseEntity = locationController.deleteLocation(id);

        assertEquals(204, responseEntity.getStatusCodeValue());
        verify(locationService, times(1)).deleteLocation(id);
    }

    @Test
    public void testUpdateLocation() {
        long id = 1L;
        Location location = new Location();
        when(locationService.updateLocation(id, location)).thenReturn(location);

        ResponseEntity<?> responseEntity = locationController.updateLocation(id, location);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(location, responseEntity.getBody());
    }


    @Test
    public void testDeleteAllLocations() {
        doNothing().when(locationService).deleted();

        locationController.deleteall();

        verify(locationService, times(1)).deleted();
    }
}
