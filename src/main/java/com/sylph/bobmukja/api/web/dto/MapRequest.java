package com.sylph.bobmukja.api.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MapRequest {

    @NotNull
    @Schema(description = "중심 좌표")
    LatLng center;

    @Schema(description = "우상단 좌표")
    LatLng topRight;

    @Schema(description = "좌하단 좌표")
    LatLng bottomLeft;

}
