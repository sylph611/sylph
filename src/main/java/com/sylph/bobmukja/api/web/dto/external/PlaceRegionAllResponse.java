package com.sylph.bobmukja.api.web.dto.external;

import com.sylph.bobmukja.api.web.dto.internal.Region;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class PlaceRegionAllResponse {

    @Schema(description = "MAIN 장소 카테고리 (대분류)")
    private List<Region> mainRegions;

    @Schema(description = "SUB 장소 카테고리 (대분류)")
    private List<Region> subRegions;

    @Builder
    public PlaceRegionAllResponse(List<Region> mainRegions, List<Region> subRegions) {
        this.mainRegions = mainRegions;
        this.subRegions = subRegions;
    }

    public static PlaceRegionAllResponse of(List<Region> mainRegions, List<Region> subRegions) {
        return PlaceRegionAllResponse.builder()
                .mainRegions(mainRegions)
                .subRegions(subRegions)
                .build();
    }
}
