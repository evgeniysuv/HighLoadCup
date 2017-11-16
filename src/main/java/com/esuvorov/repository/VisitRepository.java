package com.esuvorov.repository;

import com.esuvorov.model.Visit;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VisitRepository extends PagingAndSortingRepository<Visit, Long> {
    Optional<Visit> findById(@NonNull Long visitId);

    @Query("select v.mark, v.visitedAt, v.location.place " +
            "from Visit v " +
            "where " +
            "v.user.id = :userId and " +
            "(v.visitedAt > :fromDate or :fromDate is null) and " +
            "(v.visitedAt < :toDate or :toDate is null) and " +
            "(v.location.country = :country or :country is null) and " +
            "(v.location.distance = :distance or :distance is null)" +
            "order by v.visitedAt")
    List<Object[]> findByUserAndByDateAndByCountryAndByDistance(@NonNull
                                                                @Param("userId") Long userId,
                                                                @Param("fromDate") Long fromDate,
                                                                @Param("toDate") Long toDate,
                                                                @Param("country") String country,
                                                                @Param("distance") Long distance
    );
}
