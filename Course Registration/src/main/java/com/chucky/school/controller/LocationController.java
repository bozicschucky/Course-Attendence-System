package com.chucky.school.controller;

import com.chucky.school.domain.Location;

import com.chucky.school.service.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationServiceImpl locationService;




    @PostMapping("/create")
    public ResponseEntity<?> createLocation(
            @RequestParam long typeId,
            @RequestParam long capacity,
                @RequestParam String name,
            @RequestParam String createdBy,
            @RequestParam String locationType
    ) {

        Location locationDTO = locationService.createLocation(typeId, capacity, name,createdBy, locationType);
        return ResponseEntity.ok(locationDTO);
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getLocation(@PathVariable long id){
       Location locationDTO = locationService.getLocation(id);
       return ResponseEntity.ok(locationDTO);
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllLocations(){
       Collection<Location> locationDTOS = locationService.getAllLocations();
       return ResponseEntity.ok(locationDTOS);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable long id){
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLocation(@PathVariable long id, @RequestBody Location locationDTP){
        locationService.updateLocation(id, locationDTP);
        return ResponseEntity.ok(locationDTP);
    }

    @GetMapping("/deleteall")
    public void deleteall(){
        locationService.deleted();
    }
}
