package com.esuvorov.repository;

import com.esuvorov.model.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends PagingAndSortingRepository<Location, Long> {

    @Query("select avg(v.mark) from Visit v where v.location.id = :location")
    double getAvgMarkByLocation(@Param("location") Long location);
}
