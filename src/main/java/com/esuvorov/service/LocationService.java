package com.esuvorov.service;

import com.esuvorov.model.Location;
import com.esuvorov.repository.LocationRepository;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

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

    @SuppressWarnings("unchecked")
    public JSONObject getAverageMark(Long id, Long fromDate, Long toDate, Long fromAge, Long toAge, String gender) {
        double averageMark = repository.getAvgMarkByLocation(id, fromDate, toDate, fromAge, toAge, gender);
        String formattedAverageMark = new DecimalFormat(".##").format(averageMark);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("avg", parseDouble(formattedAverageMark));
        return jsonObject;
    }
}
