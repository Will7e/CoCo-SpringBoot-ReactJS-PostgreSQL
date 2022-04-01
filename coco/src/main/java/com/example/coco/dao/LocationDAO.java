package com.example.coco.dao;

import com.example.coco.models.Location;
import com.example.coco.repository.LocationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationDAO {
    LocationRepository repository;

    public LocationDAO(LocationRepository repository) {
        this.repository = repository;
    }


    public List<Location> getAll() {
        return (List<Location>) repository.findAll();
    }

    public void addLocation(Location location) {
        repository.save(location);
    }
}
