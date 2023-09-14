package com.sylph.bobmukja.api.domain.repository;

import com.sylph.bobmukja.api.domain.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {

}
