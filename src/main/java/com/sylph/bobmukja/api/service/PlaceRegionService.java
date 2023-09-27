package com.sylph.bobmukja.api.service;

import com.sylph.bobmukja.api.constant.PlaceType;
import com.sylph.bobmukja.api.domain.entity.PlaceRegion;
import com.sylph.bobmukja.api.domain.repository.PlaceRegionRepository;
import com.sylph.bobmukja.api.domain.specification.PlaceRegionSpecification;
import com.sylph.bobmukja.api.web.dto.external.PlaceRegionAllResponse;
import com.sylph.bobmukja.api.web.dto.external.PlaceRegionResponse;
import com.sylph.bobmukja.api.web.dto.internal.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceRegionService {

    private final PlaceRegionRepository placeRegionRepository;

    /**
     * 전체 지역 카테고리 조회
     * @return
     */
    public PlaceRegionAllResponse getAllPlaceRegions() {
        List<PlaceRegion> mainRegions = placeRegionRepository.findAllByType(PlaceType.MAIN.getCode());
        List<PlaceRegion> subRegions = placeRegionRepository.findAllByType(PlaceType.SUB.getCode());

        return PlaceRegionAllResponse.of(Region.ofList(mainRegions),Region.ofList(subRegions));
    }

    /**
     * 특정 지역 조회
     * @param placeType
     * @param upperCode
     * @return
     */
    public PlaceRegionResponse getPlaceRegions(PlaceType placeType, String upperCode) {
        List<PlaceRegion> placeRegions = placeRegionRepository.findAll(PlaceRegionSpecification.findAll(placeType, upperCode));
        return PlaceRegionResponse.of(Region.ofList(placeRegions));
    }
}
