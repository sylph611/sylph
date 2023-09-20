package com.sylph.bobmukja.api.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
public class LatLng {

    @Schema(description = "위도")
    private Double latitude;

    @Schema(description = "경도")
    private Double longitude;

    @Builder
    public LatLng(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public boolean isEmpty() {
        return latitude.isNaN() || longitude.isNaN() || latitude == 0 || longitude == 0;
    }
}
