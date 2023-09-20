package com.sylph.bobmukja.api.domain.repository;

import com.sylph.bobmukja.api.domain.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findByIdAndDeletedIsFalse(Long id);


    List<Place> findPlacesByLatitudeBetweenAndLongitudeBetween(String latitude, String latitude2, String longitude, String longitude2);

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
