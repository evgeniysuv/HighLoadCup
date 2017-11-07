package com.esuvorov.controller;

import com.esuvorov.model.Location;
import com.esuvorov.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {
    private final LocationService service;

    @Autowired
    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Location> getLocations() {
        return service.getAllLocations();
    }

    @GetMapping(value = "/{id}")
    public Location getLocation(@PathVariable long id) {
        return service.getLocation(id);
    }

    @GetMapping(value = "/{id}/avg")
    public ResultAvg avg(@PathVariable long id) {
        return new ResultAvg(id);
    }

    @AllArgsConstructor
    private class ResultAvg {
        private long avg;
    }
}
