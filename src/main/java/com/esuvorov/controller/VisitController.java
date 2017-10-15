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

    @GetMapping(value = "/users/{userId}/visits")
    public List<Visit> getVisitsByUser(@PathVariable long userId,
                                       @RequestParam(value = "fromDate", required = false) Long fromDate,
                                       @RequestParam(value = "toDate", required = false) Long toDate) {
        Sort sort = Sort.by(Sort.Direction.ASC, "visitedAt");
        if (fromDate == null && toDate == null) {
            return visitService.getVisitsByUser(userId, sort);
        } else if (fromDate == null) {
            return visitService.getVisitByUserAndVisitedAtLessThan(userId, toDate, sort);
        } else if (toDate == null) {
            return visitService.getVisitByUserAndVisitedAtMoreThan(userId, fromDate, sort);
        }

        return visitService.getVisitByUserAndWith(userId, fromDate, toDate, sort);
    }
}
