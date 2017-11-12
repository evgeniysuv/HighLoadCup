package com.esuvorov.controller;

import com.esuvorov.model.Location;
import com.esuvorov.service.LocationService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public JSONObject avg(@PathVariable Long id,
                          @RequestParam(value = "fromDate", required = false) Long fromDate,
                          @RequestParam(value = "toDate", required = false) Long toDate,
                          @RequestParam(value = "fromAge", required = false) Long fromAge,
                          @RequestParam(value = "toAge", required = false) Long toAge,
                          @RequestParam(value = "gender", required = false) String gender) {
        return service.getAverageMark(id, fromDate, toDate, fromAge, toAge, gender);
    }
}
