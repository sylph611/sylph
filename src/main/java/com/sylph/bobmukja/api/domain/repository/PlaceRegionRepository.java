package com.sylph.bobmukja.api.domain.repository;

import com.sylph.bobmukja.api.domain.entity.PlaceRegion;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PlaceRegionRepository extends JpaRepository<PlaceRegion, Long>, JpaSpecificationExecutor<PlaceRegion> {

    List<PlaceRegion> findAll(Specification<PlaceRegion> spec);

}
