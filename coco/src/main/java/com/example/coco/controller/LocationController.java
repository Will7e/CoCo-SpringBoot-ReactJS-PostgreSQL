package com.example.coco.controller;

import com.example.coco.models.Location;
import com.example.coco.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {
LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/all")
    public List<Location> getAllLocations(){
        return locationService.getAllLocations();
    }

    @PostMapping("/add")
    public void addLocation(@RequestBody Location location){
        locationService.addLocation(location);
    }
}
