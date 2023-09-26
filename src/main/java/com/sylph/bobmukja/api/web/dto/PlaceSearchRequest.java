package com.sylph.bobmukja.api.web.dto;

import com.sylph.bobmukja.api.domain.entity.Place;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
public class PlaceSearchRequest {

    @Schema(description = "장소명")
    private String name;

    @Schema(description = "장소 대분류")
    private String placeCategory;

    @Schema(description = "장소 소분류")
    private String placeSubCategory;

    @Schema(description = "지역 대분류")
    private String region;

    @Schema(description = "지역 소분류")
    private String subRegion;

    @Builder
    public PlaceSearchRequest(String name, String placeCategory, String placeSubCategory, String region, String subRegion) {
        this.name = name;
        this.placeCategory = placeCategory;
        this.placeSubCategory = placeSubCategory;
        this.region = region;
        this.subRegion = subRegion;
    }

}
