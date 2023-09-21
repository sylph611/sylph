package com.sylph.bobmukja.api.domain.repository;

import com.sylph.bobmukja.api.domain.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findByIdAndDeletedIsFalse(Long id);

    Page<Place> findPlacesByLatitudeBetweenAndLongitudeBetween(Double bottomLat, Double topLat, Double leftLng, Double rightLng, Pageable pageable);

    /*
    @Query(value = "select sum(p.progress_count) as progressCount , p.badge_id as badgeId" +
            "  from user_badge_progress p " +
            " where p.user_id = :userId " +
            " group by p.badge_id ", nativeQuery = true)
    List<BadgeProgressCountVo> findProgressCountGroupByUserAndBadge(@Param("userId") Long userId);

    interface BadgeProgressCountVo {

        Long getBadgeId();
        Integer getProgressCount();

    }*/

}
