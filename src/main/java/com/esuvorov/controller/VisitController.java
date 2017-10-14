package com.esuvorov.controller;

import com.esuvorov.model.Visit;
import com.esuvorov.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @GetMapping(value = "/users/{id}/visits")
    public List<Visit> getVisitsByUser(@PathVariable long id,
                                       @RequestParam("fromDate") long fromDate,
                                       @RequestParam("toDate") long toDate) {
        Sort ascVisitedAt = Sort.by(Sort.Direction.ASC, "visitedAt");
        return visitService.getVisitByUserAndWith(id, fromDate, toDate, ascVisitedAt);
    }
}
