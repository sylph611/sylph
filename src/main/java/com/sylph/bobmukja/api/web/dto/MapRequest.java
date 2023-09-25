package com.sylph.bobmukja.api.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MapRequest {

    @Schema(description = "중심 좌표 위도")
    private Double centerLatitude;

    @Schema(description = "중심 좌표 경도")
    private Double centerLongitude;

    @Schema(description = "우상단 좌표 위도")
    private Double topRightLatitude;

    @Schema(description = "우상단 좌표 경도")
    private Double topRightLongitude;

    @Schema(description = "좌하단 좌표 위도")
    private Double bottomLeftLatitude;

    @Schema(description = "좌하단 좌표 경도")
    private Double bottomLeftLongitude;

    public LatLng toCenterLatLng() {
        return LatLng.builder()
                .latitude(centerLatitude)
                .longitude(centerLongitude)
                .build();
    }

    public LatLng toTopRightLatLng() {
        return LatLng.builder()
                .latitude(topRightLatitude)
                .longitude(topRightLongitude)
                .build();
    }

    public LatLng toBottomLeftLatLng() {
        return LatLng.builder()
                .latitude(bottomLeftLatitude)
                .longitude(bottomLeftLongitude)
                .build();
    }

}
