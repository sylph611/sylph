package com.sylph.bobmukja.api.domain.specification;

import com.sylph.bobmukja.api.domain.entity.Place;
import com.sylph.bobmukja.api.web.dto.PlaceSearchRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

public class PlaceSpecification {

    /**
     * 이름 조회 (EQUAL)
     * @param name
     * @return
     */
    public static Specification<Place> equalsName(String name) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("name"), name);
    }

    /**
     * 이름 조회 (LIKE)
     * @param name
     * @return
     */
    public static Specification<Place> likeName(String name) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("name"),"%"+name+"%");
    }

    /**
     * ID로 조회
     * @param id
     * @return
     */
    public static Specification<Place> equalsId(Long id) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("id"), id);
    }

    /**
     * 지역 조회
     * @param region
     * @return
     */
    public static Specification<Place> equalsRegion(String region) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("region"), region);
    }

    /**
     * 상세지역 조회
     * @param subRegion
     * @return
     */
    public static Specification<Place> equalsSubRegion(String subRegion) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("subRegion"), subRegion);
    }

    /**
     * 장소 카테고리 조회
     * @param placeCategory
     * @return
     */
    public static Specification<Place> equalsPlaceCategory(String placeCategory) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("placeCategory"), placeCategory);
    }

    /**
     * 세부 카테고리 조회
     * @param placeSubCategory
     * @return
     */
    public static Specification<Place> equalsSubCategory(String placeSubCategory) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("placeSubCategory"), placeSubCategory);
    }

    public static Specification<Place> notDeleted() {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.isFalse(root.get("deleted"));
    }

    public static Specification<Place> search(PlaceSearchRequest placeSearchRequest) {
        Specification<Place> spec = (root, query, criteriaBuilder) -> null;
        spec = spec.and(PlaceSpecification.notDeleted());

        // 이름
        if(ObjectUtils.isNotEmpty(placeSearchRequest.getName())) {
            spec = spec.and(PlaceSpecification.likeName(placeSearchRequest.getName()));
        }
        // 분류
        if(ObjectUtils.isNotEmpty(placeSearchRequest.getPlaceCategory())) {
            spec = spec.and(PlaceSpecification.equalsPlaceCategory(placeSearchRequest.getPlaceCategory()));

            if(ObjectUtils.isNotEmpty(placeSearchRequest.getPlaceSubCategory())) {
                spec = spec.and(PlaceSpecification.equalsSubCategory(placeSearchRequest.getPlaceSubCategory()));
            }
        }
        // 지역
        if(ObjectUtils.isNotEmpty(placeSearchRequest.getRegion())) {
            spec = spec.and(PlaceSpecification.equalsRegion(placeSearchRequest.getRegion()));

            if(ObjectUtils.isNotEmpty(placeSearchRequest.getSubRegion())) {
                spec = spec.and(PlaceSpecification.equalsSubRegion(placeSearchRequest.getSubRegion()));
            }
        }

        return spec;
    }
}
