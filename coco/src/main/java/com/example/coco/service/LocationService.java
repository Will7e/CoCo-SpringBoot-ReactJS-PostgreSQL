package com.example.coco.service;

import com.example.coco.dao.LocationDAO;
import com.example.coco.models.Location;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private LocationDAO locationDAO;

    public LocationService(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public List<Location> getAllLocations() {
        return locationDAO.getAll();
    }

    public void addLocation(Location location) {
        locationDAO.addLocation(location);
    }
}
