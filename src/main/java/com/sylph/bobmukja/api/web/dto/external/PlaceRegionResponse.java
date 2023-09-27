package com.sylph.bobmukja.api.web.dto.external;

import com.sylph.bobmukja.api.web.dto.internal.Region;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class PlaceRegionResponse {

    @Schema(description = "장소 지역 리스트")
    private List<Region> regions;

    @Builder
    public PlaceRegionResponse(List<Region> regions) {
        this.regions = regions;
    }

    public static PlaceRegionResponse of(List<Region> regions) {
        return PlaceRegionResponse.builder()
                .regions(regions)
                .build();
    }
}
