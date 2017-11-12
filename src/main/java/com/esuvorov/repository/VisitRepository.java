package com.esuvorov.repository;

import com.esuvorov.model.Visit;
import lombok.NonNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface VisitRepository extends PagingAndSortingRepository<Visit, Long> {
    Optional<Visit> findById(@NonNull Long visitId);

    List<Visit> findByUserId(Long id, Sort sort);

    List<Visit> findByUserIdAndVisitedAtGreaterThanAndVisitedAtLessThan(Long userId, Long visitedAt, Long visitedAt2, Sort sort);

    List<Visit> findByUserIdAndVisitedAtGreaterThan(Long userId, Long visitedAt, Sort sort);

    List<Visit> findByUserAndVisitedAtLessThan(Long userId, Long visitedAt, Sort sort);

    @Query("select v.mark, v.visitedAt, v.location.place from Visit v order by v.visitedAt")
    List<String[]> findByUserETC();
}
