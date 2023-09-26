package com.sylph.bobmukja.api.domain.repository;

import com.sylph.bobmukja.api.domain.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long>, JpaSpecificationExecutor<Place> {

    Optional<Place> findByIdAndDeletedIsFalse(Long id);

    Page<Place> findAll(Specification<Place> spec, Pageable pageable);

    Page<Place> findPlacesByLatitudeBetweenAndLongitudeBetween(Double bottomLat, Double topLat, Double leftLng, Double rightLng, Pageable pageable);

    @Query(value = " select p.* " +
                   "   from place p " +
                   "  where p.deleted != true " +
                   "  order by ST_Distance_Sphere(POINT(:lng, :lat), POINT(p.longitude, p.latitude)) desc",
           countQuery = " select count(p) " +
                        " from place p",
           nativeQuery = true)
    Page<Place> findPlacesByCenter(@Param("lat") Double lat, @Param("lng") Double lng, Pageable pageable);

}
