package com.esuvorov.repository;

import com.esuvorov.model.Location;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends PagingAndSortingRepository<Location, Long> {

    @Query("select avg(v.mark) from Visit v " +
            "where " +
            "v.location.id = :locationId and " +
            "(v.visitedAt >= :fromDate or :fromDate is null) and " +
            "(v.visitedAt <= :toDate or :toDate is null) and " +
            "(v.user.birthDate >= :fromAge or :fromAge is null) and " +
            "(v.user.birthDate <= :toAge or :toAge is null) and " +
            "(v.user.gender = :gender or :gender is null)")
    double getAvgMarkByLocation(@NonNull @Param("locationId") Long locationId,
                                @Param("fromDate") Long fromDate,
                                @Param("toDate") Long toDate,
                                @Param("fromAge") Long toAge,
                                @Param("toAge") Long fromAge,
                                @Param("gender") String gender);
}
