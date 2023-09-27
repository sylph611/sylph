package com.sylph.bobmukja.api.service;

import com.sylph.bobmukja.api.constant.PlaceType;
import com.sylph.bobmukja.api.domain.entity.PlaceCategory;
import com.sylph.bobmukja.api.domain.repository.PlaceCategoryRepository;
import com.sylph.bobmukja.api.domain.specification.PlaceCategorySpecification;
import com.sylph.bobmukja.api.web.dto.external.PlaceCategoryAllResponse;
import com.sylph.bobmukja.api.web.dto.external.PlaceCategoryResponse;
import com.sylph.bobmukja.api.web.dto.internal.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceCategoryService {

    private final PlaceCategoryRepository placeCategoryRepository;

    /**
     * 전체 장소 카테고리 조회
     * @return
     */
    public PlaceCategoryAllResponse getAllPlaceCategories() {
        List<PlaceCategory> mainCategories = placeCategoryRepository.findAllByType(PlaceType.MAIN.getCode());
        List<PlaceCategory> subCategories = placeCategoryRepository.findAllByType(PlaceType.SUB.getCode());

        return PlaceCategoryAllResponse.of(Category.ofList(mainCategories),Category.ofList(subCategories));
    }

    /**
     * 특정 카테고리 조회
     * @param placeType
     * @param upperCode
     * @return
     */
    public PlaceCategoryResponse getPlaceCategories(PlaceType placeType, String upperCode) {
        List<PlaceCategory> placeCategories = placeCategoryRepository.findAll(PlaceCategorySpecification.findAll(placeType, upperCode));
        return PlaceCategoryResponse.of(Category.ofList(placeCategories));
    }
}
