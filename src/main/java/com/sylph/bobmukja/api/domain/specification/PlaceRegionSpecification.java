package com.sylph.bobmukja.api.domain.specification;

import com.sylph.bobmukja.api.constant.PlaceType;
import com.sylph.bobmukja.api.domain.entity.PlaceRegion;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class PlaceRegionSpecification {

    /**
     * 타입 조회
     * @param placeType
     * @return
     */
    public static Specification<PlaceRegion> equalsPlaceType(PlaceType placeType) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("type"), placeType.getCode());
    }

    /**
     * 상위코드 조회
     * @param upperCode
     * @return
     */
    public static Specification<PlaceRegion> equalsUpperCode(String upperCode) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("upperCode"), StringUtils.upperCase(upperCode));
    }

    public static Specification<PlaceRegion> findAll(PlaceType placeType, String upperCode) {
        Specification<PlaceRegion> spec = (root, query, criteriaBuilder) -> null;

        // 타입
        if(ObjectUtils.isNotEmpty(placeType)) {
            spec = spec.and(PlaceRegionSpecification.equalsPlaceType(placeType));
        }
        // 상위 코드 (TYPE이 SUB일때만 작동)
        if(ObjectUtils.isNotEmpty(upperCode)
                && PlaceType.SUB.equals(placeType)) {
            spec = spec.and(PlaceRegionSpecification.equalsUpperCode(upperCode));
        }

        return spec;
    }
}
