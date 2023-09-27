package com.sylph.bobmukja.api.domain.repository;

import com.sylph.bobmukja.api.domain.entity.PlaceCategory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PlaceCategoryRepository extends JpaRepository<PlaceCategory, Long> , JpaSpecificationExecutor<PlaceCategory> {
    List<PlaceCategory> findAllByType(String type);

    List<PlaceCategory> findAll(Specification<PlaceCategory> spec);
}
