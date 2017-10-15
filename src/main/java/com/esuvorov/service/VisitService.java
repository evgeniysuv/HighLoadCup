package com.esuvorov.service;

import com.esuvorov.model.Visit;
import com.esuvorov.repository.VisitRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public List<Visit> getVisitsByUser(long id, Sort sort) {
        return repository.findByUserId(id, sort);
    }

    public List<Visit> getVisitByUserAndWith(long userId, Long dateFrom, Long toDate, Sort sort) {
        return repository.findByUserIdAndVisitedAtGreaterThanAndVisitedAtLessThan(userId, dateFrom, toDate, sort);
    }

    public List<Visit> getVisitByUserAndVisitedAtMoreThan(long userId, Long fromDate, Sort sort) {
        return repository.findByUserIdAndVisitedAtGreaterThan(userId, fromDate, sort);
    }

    public List<Visit> getVisitByUserAndVisitedAtLessThan(long userId, Long toDate, Sort sort) {
        return repository.findByUserAndVisitedAtLessThan(userId, toDate, sort);
    }
}
