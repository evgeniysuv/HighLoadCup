package com.esuvorov.service;

import com.esuvorov.model.Visit;
import com.esuvorov.repository.VisitRepository;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitService {
    private static final Logger LOGGER = Logger.getLogger(VisitService.class);
    private final VisitRepository repository;

    @Autowired
    public VisitService(VisitRepository repository) {
        this.repository = repository;
    }

    public List<Visit> getVisits() {
        List<Visit> visits = new ArrayList<>();
        repository.findAll().forEach(visits::add);
        return visits;
    }

    public Visit getVisitById(long visitId) {
        return repository.findById(visitId).orElse(null);
    }

    @SuppressWarnings("unchecked")
    public JSONObject findVisits() {
        JSONArray visitsArray = new JSONArray();
        repository.findByUserAndByDateAndByCountryAndByDistance().forEach(object -> {
            JSONObject visit = new JSONObject();
            visit.put("mark", object[0]);
            visit.put("visited_at", object[1]);
            visit.put("place", object[2]);
            visitsArray.add(visit);
        });

        JSONObject visitsJson = new JSONObject();
        visitsJson.put("visits", visitsArray);
        return visitsJson;
    }
}
