package com.esuvorov.service;

import com.esuvorov.model.Location;
import com.esuvorov.repository.LocationRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {
    private static final Logger LOGGER = Logger.getLogger(LocationService.class);
    private final LocationRepository repository;

    @Autowired
    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        repository.findAll().forEach(locations::add);
        return locations;
    }

    public Location getLocation(long id) {
        return repository.findById(id).orElse(null);
    }

    public double getAverageMark(long id) {
        return repository.getAvgMarkByLocation(id);
    }
}
