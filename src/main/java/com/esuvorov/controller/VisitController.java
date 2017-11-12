package com.esuvorov.controller;

import com.esuvorov.model.Visit;
import com.esuvorov.service.VisitService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class VisitController {
    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping(value = "/visits")
    public List<Visit> getVisits() {
        return visitService.getVisits();
    }

    @GetMapping(value = "/visits/{visitId}")
    public Visit getVisitById(@PathVariable long visitId) {
        return visitService.getVisitById(visitId);
    }

    @GetMapping(value = "/users/{userId}/visits")
    public JSONObject getVisitsByUser(@PathVariable long userId,
                                      @RequestParam(value = "fromDate", required = false) Long fromDate,
                                      @RequestParam(value = "toDate", required = false) Long toDate) {
        return visitService.findByUserETC();
    }
}
