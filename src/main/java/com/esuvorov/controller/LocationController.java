package com.esuvorov.controller;

import com.esuvorov.model.Location;
import com.esuvorov.service.LocationService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
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
    public ResultAvg avg(@PathVariable Long id,
                         @RequestParam(value = "fromDate", required = false) Long fromDate,
                         @RequestParam(value = "toDate", required = false) Long toDate,
                         @RequestParam(value = "fromAge", required = false) Long fromAge,
                         @RequestParam(value = "toAge", required = false) Long toAge,
                         @RequestParam(value = "gender", required = false) String gender) {
        double averageMark = service.getAverageMark(id, fromDate, toDate, fromAge, toAge, gender);
        return new ResultAvg(averageMark);
    }

    @Data
    private class ResultAvg {
        private double avg;

        ResultAvg(double avg) {
            String formattedAvgMark = new DecimalFormat(".##").format(avg);
            this.avg = Double.parseDouble(formattedAvgMark);
        }
    }
}
