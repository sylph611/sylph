package com.sylph.bobmukja.api.domain.repository;

import com.sylph.bobmukja.api.domain.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {
    Optional<Review> findByIdAndDeletedIsFalse(Long id);

    List<Review> findByPlaceIdAndDeletedIsFalse(Long id);

    Page<Review> findAllByPlaceId(Long placeId, Specification<Review> spec, Pageable pageable);

}
