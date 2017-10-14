package com.esuvorov.repository;

import com.esuvorov.model.Visit;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface VisitRepository extends PagingAndSortingRepository<Visit, Long> {
    List<Visit> findByUserId(Long id, Sort sort);

    List<Visit> findByUserIdAndVisitedAtGreaterThanAndVisitedAtLessThan(Long userId, Long visitedAt, Long visitedAt2, Sort sort);
}
